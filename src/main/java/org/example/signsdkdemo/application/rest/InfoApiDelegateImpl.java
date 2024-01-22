package org.example.signsdkdemo.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.signsdkdemo.application.rest.mappers.CertificateMapper;
import org.example.signsdkdemo.application.rest.mappers.DeviceMapper;
import org.example.signsdkdemo.generated.application.api.InfoApiDelegate;
import org.example.signsdkdemo.generated.application.model.InitData;
import org.example.signsdkdemo.generated.application.model.LocalSignCertificateDTO;
import org.example.signsdkdemo.generated.application.model.LocalSignDeviceDTO;
import org.example.signsdkdemo.infrastructure.models.Session;
import org.example.signsdkdemo.domain.services.DeviceService;
import org.example.signsdkdemo.domain.services.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Log4j2
@RestController
@Service
@RequiredArgsConstructor
public class InfoApiDelegateImpl implements InfoApiDelegate {

    @Value("${server.port}")
    private String port;

    private final DeviceService deviceService;

    private final SessionService sessions;

    @Override
    public ResponseEntity<InitData> initSession(String authorization, String xSignOrigin) {
        Session session =sessions.checkAuth(authorization, xSignOrigin);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Sign-SDK-Session-ID", session.getSessionToken());
        responseHeaders.set("X-Sign-SDK-Session-Duration", String.valueOf(session.getDuration()));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new InitData()
                        .port(Integer.valueOf(port))
                        .appVersion("1.0.0")
                        .lastUpdate("18/01/2024 00:11:32"));
    }

    @Override
    public ResponseEntity<LocalSignCertificateDTO> retrieveCertificate(String xSignSDKSessionID, String xSignOrigin, String deviceSerial, Integer slot) {
        sessions.checkSession(xSignSDKSessionID, xSignOrigin);
        return ResponseEntity.ok().body(CertificateMapper.entityToDTO(deviceService.getCertificate(deviceSerial, slot)));
    }

    @Override
    public ResponseEntity<List<LocalSignDeviceDTO>> retrieveDevices(String xSignSDKSessionID, String xSignOrigin) {
        sessions.checkSession(xSignSDKSessionID, xSignOrigin);
        return ResponseEntity.ok().body(DeviceMapper.convertDevices(deviceService.getDevices()));
    }

}
