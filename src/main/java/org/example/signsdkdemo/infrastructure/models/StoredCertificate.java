package org.example.signsdkdemo.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("certificates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoredCertificate {

        @Id
        String _id;
        String serialNumber;
        String deviceId;
        int slot;
        String type;
        String creationDate;
        String expireDate;
        StoredSubject subject;
        StoredIssuer issuer;
        String criticalKeyUsage;
        String secret;
        int maxPinLength;
        int minPinLength;
        String pin;
        String pun;
        int    pinRetries;

}
