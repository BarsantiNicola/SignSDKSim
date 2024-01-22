package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super("Resource not found", HttpStatus.NOT_FOUND, ErrorManager.RESOURCE_NOT_FOUND);
    }
}
