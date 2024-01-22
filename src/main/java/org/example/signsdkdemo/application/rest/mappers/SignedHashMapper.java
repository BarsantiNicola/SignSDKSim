package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.domain.models.SignedHash;
import org.example.signsdkdemo.generated.application.model.LocalSignExecuteSignHashResponseDTO;

import java.util.List;

public class SignedHashMapper {

    public static LocalSignExecuteSignHashResponseDTO entityToDTO(List<SignedHash> hashes){
        LocalSignExecuteSignHashResponseDTO response = new LocalSignExecuteSignHashResponseDTO();
        hashes.forEach( hash -> response.put(hash.getHash(), hash.getSignature()));
        return response;
    }
}
