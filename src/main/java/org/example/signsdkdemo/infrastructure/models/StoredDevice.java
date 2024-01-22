package org.example.signsdkdemo.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("devices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoredDevice {

    String firmwareManufacturer;
    String description;
    String model;
    String serial;

}
