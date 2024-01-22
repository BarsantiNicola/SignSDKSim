package org.example.signsdkdemo.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoredIssuer {
    String issuerName;
    String country;
}
