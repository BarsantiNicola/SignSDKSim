package org.example.signsdkdemo.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.signsdkdemo.application.rest.mappers.HashMapper;
import org.example.signsdkdemo.application.rest.mappers.SignedHashMapper;
import org.example.signsdkdemo.application.rest.mappers.ValidationResultMapper;
import org.example.signsdkdemo.domain.models.SignedHash;
import org.example.signsdkdemo.domain.models.ValidationResult;
import org.example.signsdkdemo.generated.application.api.CryptoApiDelegate;
import org.example.signsdkdemo.generated.application.model.LocalSignExecuteSignHashRequest;
import org.example.signsdkdemo.generated.application.model.LocalSignExecuteSignHashResponseDTO;
import org.example.signsdkdemo.generated.application.model.LocalSignValidatePinResponseDTO;
import org.example.signsdkdemo.generated.application.model.PinDTO;
import org.example.signsdkdemo.domain.services.CryptoService;
import org.example.signsdkdemo.domain.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@Service
@RequiredArgsConstructor
public class CryptoApiDelegateImpl implements CryptoApiDelegate {

    private final CryptoService cryptoService;
    private final SessionService sessionService;

    @Override
    public ResponseEntity<LocalSignExecuteSignHashResponseDTO> signHashes(String xSignSDKSessionID, String xSignOrigin, String deviceSerial, Integer slot, String pin, LocalSignExecuteSignHashRequest body) {
        sessionService.checkSession(xSignSDKSessionID, xSignOrigin);
        List<SignedHash> hashes=cryptoService.signHashes(HashMapper.DTOtoEntity(body), pin, deviceSerial, slot);
        return ResponseEntity.ok(SignedHashMapper.entityToDTO(hashes));
    }

    @Override
    public ResponseEntity<LocalSignValidatePinResponseDTO> validateCertificate(String xSignSDKSessionID, String xSignOrigin, String deviceSerial, Integer slot, PinDTO body) {
        sessionService.checkSession(xSignSDKSessionID, xSignOrigin);
        ValidationResult result = cryptoService.enableCertificate(body.getPin(), deviceSerial, slot);
        return ResponseEntity.ok(ValidationResultMapper.entityToDTO(result));
    }

}
