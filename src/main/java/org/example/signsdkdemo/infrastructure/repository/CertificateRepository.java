package org.example.signsdkdemo.infrastructure.repository;

import org.example.signsdkdemo.infrastructure.models.StoredCertificate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends MongoRepository<StoredCertificate,String> {

    Optional<StoredCertificate> getStoredCertificateBySlotAndDeviceId(int slot, String deviceId);
    List<StoredCertificate> getStoredCertificatesByDeviceId(String deviceId);

}
