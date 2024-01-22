package org.example.signsdkdemo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private String firmwareManufacturer;
    private String description;
    private String model;
    private String serial;

    private List<StoredCertificate> certificates;
}
