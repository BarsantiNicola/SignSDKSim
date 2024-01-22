package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.generated.application.model.LocalSignSubjectDTO;
import org.example.signsdkdemo.infrastructure.models.StoredSubject;

public class SubjectMapper {
    public static LocalSignSubjectDTO entityToDTO(StoredSubject subject){
        return new LocalSignSubjectDTO()
                    .commonName(subject.getCommonName())
                    .firstName(subject.getFirstName())
                    .lastName(subject.getLastName())
                    .identifier(subject.getIdentifier())
                    .country(subject.getCountry());
    }
}
