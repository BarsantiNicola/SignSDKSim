package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class InvalidPinException extends BaseException{


    public InvalidPinException() {
        super("Invalid Pin", HttpStatus.UNAUTHORIZED, ErrorManager.UNAUTHORIZED);
    }

}
