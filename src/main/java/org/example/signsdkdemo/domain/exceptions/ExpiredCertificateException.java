package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class ExpiredCertificateException extends BaseException{
    public ExpiredCertificateException() {
        super("The selected certificate is not usable", HttpStatus.BAD_REQUEST, ErrorManager.EXPIRED_CERT);
    }
}
