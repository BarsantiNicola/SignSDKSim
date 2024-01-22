package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class InternalErrorException extends BaseException{

    public InternalErrorException() {
        super("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, ErrorManager.GENERIC_INTERNAL_SERVER_ERROR);
    }

}
