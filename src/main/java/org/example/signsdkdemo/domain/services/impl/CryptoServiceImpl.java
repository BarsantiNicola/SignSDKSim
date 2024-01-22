package org.example.signsdkdemo.domain.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.signsdkdemo.domain.exceptions.*;
import org.example.signsdkdemo.domain.models.SignedHash;
import org.example.signsdkdemo.domain.models.UnsignedHash;
import org.example.signsdkdemo.domain.models.ValidationResult;
import org.example.signsdkdemo.domain.services.CryptoService;
import org.example.signsdkdemo.domain.services.DeviceService;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Hex;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final DeviceService deviceService;
    @Override
    public List<SignedHash> signHashes(List<UnsignedHash> hashes, String pin, String deviceId, int slot){
        StoredCertificate certificate = deviceService.getCertificate(deviceId, slot);
        if( enterPin(pin, certificate)){
            return hashes.stream().map( hash -> new SignedHash(hash.getHash(), makeSignature(hash.getHash(), certificate))).toList();
        }
        if( certificate.getPinRetries() < 0 ){
            throw new CertificateLockedException();
        }
        certificate.setPinRetries(certificate.getPinRetries()-1);
        deviceService.updateCertificate(certificate);
        throw new InvalidPinException();
    }

    @Override
    public ValidationResult enableCertificate(String pin, String deviceId, int slot) {
        StoredCertificate certificate = deviceService.getCertificate(deviceId, slot);
        if( certificate.getPinRetries() < 1 ) {
            if (unlockCertificate(pin, certificate)) {
                return ValidationResult.builder().isValid(true).isPinLastTry(false).isPinLocked(false).build();
            }
            return ValidationResult.builder().isValid(false).isPinLastTry(true).isPinLocked(true).build();
        }
        if( !enterPin(pin, certificate)){
            certificate.setPinRetries(certificate.getPinRetries()-1);
            deviceService.updateCertificate(certificate);
        }
        return ValidationResult
                .builder()
                .isValid(enterPin(pin, certificate))
                .isPinLastTry(certificate.getPinRetries() == 1)
                .isPinLocked(certificate.getPinRetries()<1).build();
    }

    @SneakyThrows
    private String makeSignature(String hash, StoredCertificate certificate){
        checkValidity(certificate);
        checkUsability(certificate);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return Hex.encodeHexString(digest.digest(
                (hash + certificate.getSecret()).getBytes(StandardCharsets.UTF_8)));
    }

    private void checkUsability(StoredCertificate certificate){
        if( !certificate.getCriticalKeyUsage().equals("non-repudiation")){
            throw new InvalidCertificateException();
        }
    }
    private void checkValidity(StoredCertificate certificate){
        final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        Instant startTime = Instant.from(formatter.parse(certificate.getCreationDate()));
        Instant expireTime = Instant.from(formatter.parse(certificate.getExpireDate()));
        Instant now = Instant.now();
        if( startTime.isAfter(now) || expireTime.isBefore(now)){
            throw new ExpiredCertificateException();
        }
    }
    private boolean enterPin(String pin, StoredCertificate certificate){
        return certificate.getPin().equals(pin) && certificate.getPinRetries() > 0;
    }

    private boolean enterPun(String pun, StoredCertificate certificate){
        return certificate.getPun().equals(pun);
    }
    private boolean unlockCertificate(String pun, StoredCertificate certificate) {
        if( enterPun(pun, certificate)){
            certificate.setPinRetries(3);
            deviceService.updateCertificate(certificate);
            return true;
        }
        return false;
    }

}
