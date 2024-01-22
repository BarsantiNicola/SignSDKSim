package org.example.signsdkdemo.domain.services;

import org.example.signsdkdemo.domain.models.SignedHash;
import org.example.signsdkdemo.domain.models.UnsignedHash;
import org.example.signsdkdemo.domain.models.ValidationResult;

import java.util.List;

public interface CryptoService {

    List<SignedHash> signHashes(List<UnsignedHash> hashes, String pin, String deviceId, int slot);
    ValidationResult enableCertificate(String pin, String deviceId, int slot);

}
