package org.example.signsdkdemo.domain.exceptions.handler;

import lombok.extern.log4j.Log4j2;
import org.example.signsdkdemo.domain.exceptions.*;
import org.example.signsdkdemo.domain.exceptions.errors.ErrorManager;
import org.example.signsdkdemo.generated.application.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
@Log4j2
public class SignSdkExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalErrorException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.GENERIC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPinException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPinException(InvalidPinException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.INVALID_PIN);
    }

    @ExceptionHandler(CertificateLockedException.class)
    public ResponseEntity<ErrorResponse> handleCertificateLockedException(CertificateLockedException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.CERT_LOCKED);
    }

    @ExceptionHandler(InvalidCertificateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCertificateException(InvalidCertificateException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.INVALID_CERT);
    }

    @ExceptionHandler(ExpiredCertificateException.class)
    public ResponseEntity<ErrorResponse> handleExpiredCertificateException(ExpiredCertificateException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.EXPIRED_CERT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundxception(NotFoundException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchAlgorithmException(NoSuchAlgorithmException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.GENERIC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.GENERIC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request){
        logError(ex, request);
        return newResponseEntity(request, ErrorManager.GENERIC_INTERNAL_SERVER_ERROR);
    }

    private  ResponseEntity<ErrorResponse> newResponseEntity(WebRequest request, ErrorManager error){
        return new ResponseEntity<>(
                newErrorResponse(request,error.getCode(),error.getDescription()),
                error.getStatus()
        );
    }
    private ErrorResponse newErrorResponse(WebRequest request, String errorCode, String message){

        return new ErrorResponse()
                    .errorCode(errorCode)
                    .errorMessage(message)
                .timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
                .uri(((ServletWebRequest) request).getRequest().getRequestURI());
    }

    private void logError(Exception ex,WebRequest request){
        log.error(
                "An error happened while calling {} API {}",
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                ex.getMessage(), ex
        );
    }
}
