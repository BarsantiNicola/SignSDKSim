package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.domain.models.ValidationResult;
import org.example.signsdkdemo.generated.application.model.LocalSignValidatePinResponseDTO;

public class ValidationResultMapper {

    public static LocalSignValidatePinResponseDTO entityToDTO(ValidationResult validation){
        return new LocalSignValidatePinResponseDTO()
                    .valid(validation.isValid())
                    .isPinFinalTry(validation.isPinLastTry())
                    .isPinLocked(validation.isPinLocked());

    }
}
