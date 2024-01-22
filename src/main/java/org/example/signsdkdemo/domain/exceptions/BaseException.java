package org.example.signsdkdemo.domain.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.signsdkdemo.domain.exceptions.errors.IErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@EqualsAndHashCode(callSuper=false)
public class BaseException extends RuntimeException{

    private final transient IErrorCode errorCode;
    private final HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus, IErrorCode errorCode, Throwable t){
        super(message,t);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public BaseException( String message, HttpStatus httpStatus, IErrorCode errorCode){
        this(message, httpStatus, errorCode, null);
    }
}
