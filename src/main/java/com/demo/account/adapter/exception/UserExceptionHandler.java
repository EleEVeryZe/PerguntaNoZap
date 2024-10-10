package com.demo.account.adapter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.demo.account.adapter.exception.dto.ApiError;
import com.demo.account.domain.exception.UserRegistrationException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(UserRegistrationException ex) {
        LOGGER.debug(ex.getMessage());
        return new ResponseEntity<>(new ApiError(ex.getErrorCode(), ex.getMessage()),
                ex.getErrorCode());
    }


}
