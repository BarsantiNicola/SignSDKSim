package org.example.signsdkdemo.domain.services;

import org.example.signsdkdemo.domain.models.Device;
import org.example.signsdkdemo.infrastructure.models.StoredCertificate;

import java.util.List;

public interface DeviceService {

    List<Device> getDevices();

    StoredCertificate getCertificate(String deviceId, int slot);

    void updateCertificate(StoredCertificate certificate);
}
