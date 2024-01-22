package org.example.signsdkdemo.domain.exceptions.errors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access= AccessLevel.PRIVATE)
public class ErrorManager implements IErrorCode{

    private static final String ERROR_IE_CODE = "IEC_";
    private static final String ERROR_AUTH_CODE = "AC_";

    private final String code;
    private final String description;
    private final HttpStatus status;

    public static final ErrorManager GENERIC_INTERNAL_SERVER_ERROR = new ErrorManager(
            ERROR_IE_CODE + "001",
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    public static final ErrorManager BAD_REQUEST = new ErrorManager(
            ERROR_IE_CODE + "002",
            "Invalid Input provided",
            HttpStatus.BAD_REQUEST
    );

    public static final ErrorManager RESOURCE_NOT_FOUND = new ErrorManager(
            ERROR_IE_CODE + "003",
            "Resource not found",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    public static final ErrorManager INVALID_CERT = new ErrorManager(
            ERROR_IE_CODE + "004",
            "The used certificate cannot be used to make signatures[confidentiality certificate]",
            HttpStatus.BAD_REQUEST
    );

    public static final ErrorManager EXPIRED_CERT = new ErrorManager(
            ERROR_IE_CODE + "005",
            "The used certificate is expired and cannot be used",
            HttpStatus.BAD_REQUEST
    );

    public static final ErrorManager UNAUTHORIZED = new ErrorManager(
            ERROR_AUTH_CODE + "001",
            "You are not authorized",
            HttpStatus.UNAUTHORIZED
    );

    public static final ErrorManager INVALID_PIN = new ErrorManager(
            ERROR_AUTH_CODE + "002",
            "Invalid PIN provided",
            HttpStatus.UNAUTHORIZED
    );

    public static final ErrorManager CERT_LOCKED = new ErrorManager(
            ERROR_AUTH_CODE + "003",
            "Certificate locked. Enter the PUN on /validate to unlock it",
            HttpStatus.UNAUTHORIZED
    );

}
