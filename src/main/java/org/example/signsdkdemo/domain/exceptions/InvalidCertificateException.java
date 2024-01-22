package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class InvalidCertificateException extends BaseException{
    public InvalidCertificateException() {
        super("The given certificate cannot be used to make sign(confidentiality certificate)", HttpStatus.BAD_REQUEST, ErrorManager.INVALID_CERT);
    }
}
