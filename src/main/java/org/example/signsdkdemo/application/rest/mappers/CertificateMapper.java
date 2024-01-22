package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.generated.application.model.LocalSignCertificateDTO;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;

public class CertificateMapper {

    public static LocalSignCertificateDTO entityToDTO(StoredCertificate certificate){
        return new LocalSignCertificateDTO()
                    .slot(certificate.getSlot())
                    .serialNumber(certificate.getSerialNumber())
                    .type(certificate.getType())
                    .startDate(certificate.getCreationDate())
                    .endDate(certificate.getExpireDate())
                    .subject(SubjectMapper.entityToDTO(certificate.getSubject()))
                    .issuer(IssuerMapper.entityToDTO(certificate.getIssuer()))
                    .criticalKeyUsage(certificate.getCriticalKeyUsage())
                    .maxPinLength(certificate.getMaxPinLength())
                    .minPinLength(certificate.getMinPinLength());
    }
}
