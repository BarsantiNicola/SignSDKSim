package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.domain.models.UnsignedHash;
import org.example.signsdkdemo.generated.application.model.LocalSignExecuteSignHashRequest;

import java.util.List;

public class HashMapper {

    public static List<UnsignedHash> DTOtoEntity(LocalSignExecuteSignHashRequest hashes){
        return hashes.getHashesToSign().stream().map(UnsignedHash::new).toList();
    }
}
