package org.example.signsdkdemo.application.rest.mappers;

import org.example.signsdkdemo.domain.models.Device;
import org.example.signsdkdemo.generated.application.model.LocalSignCertificateDTO;
import org.example.signsdkdemo.generated.application.model.LocalSignDeviceDTO;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;

import java.util.List;

public class DeviceMapper {

    public static LocalSignDeviceDTO entityToDTO(Device device){
        return new LocalSignDeviceDTO()
                .firmwareManufacturer(device.getFirmwareManufacturer())
                .description(device.getDescription())
                .model(device.getModel())
                .serial(device.getSerial())
                .certificates(convertCertificates(device.getCertificates()));
    }

    static public List<LocalSignCertificateDTO> convertCertificates( List<StoredCertificate> certificateList ){
        return certificateList.stream().map(CertificateMapper::entityToDTO).toList();
    }

    static public List<LocalSignDeviceDTO> convertDevices(List<Device> devices){
        return devices.stream().map(DeviceMapper::entityToDTO).toList();
    }
}
