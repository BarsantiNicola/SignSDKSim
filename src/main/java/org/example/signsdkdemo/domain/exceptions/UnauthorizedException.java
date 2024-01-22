package org.example.signsdkdemo.domain.exceptions;

import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException{

    public UnauthorizedException(){
        super("Unauthorized request", HttpStatus.UNAUTHORIZED, ErrorManager.UNAUTHORIZED);
    }
}
