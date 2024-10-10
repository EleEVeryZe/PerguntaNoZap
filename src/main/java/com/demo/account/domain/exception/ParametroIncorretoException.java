package com.demo.account.domain.exception;

import org.springframework.http.HttpStatus;

public class ParametroIncorretoException extends RuntimeException {
    private final HttpStatus httpErrorCode;
    private final String errorMsg;

    public ParametroIncorretoException(String errorMsg, HttpStatus httpErrorCode, Throwable cause) {
        super(errorMsg, cause);
        this.httpErrorCode = httpErrorCode;
        this.errorMsg = errorMsg;
    }

    public ParametroIncorretoException(String errorMsg, HttpStatus httpErrorCode) {
        super(errorMsg);
        this.httpErrorCode = httpErrorCode;
        this.errorMsg = errorMsg;
    }


    public HttpStatus getErrorCode() {
        return httpErrorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
