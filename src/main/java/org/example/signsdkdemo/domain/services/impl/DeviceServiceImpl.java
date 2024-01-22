package org.example.signsdkdemo.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.signsdkdemo.domain.mappers.StoredDeviceMapper;
import org.example.signsdkdemo.domain.models.Device;
import org.example.signsdkdemo.domain.services.DeviceService;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;
import org.example.signsdkdemo.domain.exceptions.NotFoundException;
import org.example.signsdkdemo.infrastructure.repository.CertificateRepository;
import org.example.signsdkdemo.infrastructure.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final CertificateRepository certificateRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public List<Device> getDevices() {
        return deviceRepository.findAll().stream().map(
                device ->{
                    List<StoredCertificate> certificates = certificateRepository.getStoredCertificatesByDeviceId(device.getSerial());
                    return StoredDeviceMapper.entityToDTO(device, certificates);
                }
        ).toList();
    }

    @Override
    public StoredCertificate getCertificate(String deviceId, int slot) {
        System.out.println("Searching device for: " + deviceId +" slot: " + slot);

        return certificateRepository.getStoredCertificateBySlotAndDeviceId(slot, deviceId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void updateCertificate(StoredCertificate certificate) {
        certificateRepository.save(certificate);
    }
}
