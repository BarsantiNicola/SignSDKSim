package org.example.signsdkdemo.domain.mappers;

import org.example.signsdkdemo.domain.models.Device;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;
import org.example.signsdkdemo.infrastructure.models.StoredDevice;
import java.util.List;

public class StoredDeviceMapper {

    public static Device entityToDTO(StoredDevice device, List<StoredCertificate> certificates){
        return Device.builder()
                .firmwareManufacturer(device.getFirmwareManufacturer())
                .description(device.getDescription())
                .model(device.getModel())
                .serial(device.getSerial())
                .certificates(certificates).build();
    }


}
