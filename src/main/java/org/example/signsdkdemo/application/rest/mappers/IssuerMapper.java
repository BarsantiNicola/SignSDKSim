package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.generated.application.model.LocalSignIssuerDTO;
import org.example.signsdkdemo.infrastructure.models.StoredIssuer;

public class IssuerMapper {
    public static LocalSignIssuerDTO entityToDTO(StoredIssuer issuer){
        return new LocalSignIssuerDTO()
                    .issuerName(issuer.getIssuerName())
                    .country(issuer.getCountry());
    }
}
