package com.demo.account.adapter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.demo.account.adapter.exception.dto.ApiError;
import com.demo.account.domain.exception.ParametroIncorretoException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BussinessExceptionHandler.class);

    @ExceptionHandler(ParametroIncorretoException.class)
    public ResponseEntity<Object> handleRegistrationException(ParametroIncorretoException ex) {
        LOGGER.debug(ex.getMessage());
        return new ResponseEntity<>(new ApiError(ex.getErrorCode(), ex.getMessage()),
                ex.getErrorCode());
    }


    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
