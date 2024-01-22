package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class CertificateLockedException extends BaseException{
    public CertificateLockedException() {
        super("Certificate Locked. Enter the PUN on /validate to unlock it", HttpStatus.UNAUTHORIZED, ErrorManager.CERT_LOCKED);
    }
}
