package com.demo.account.adapter.exception.dto;

import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus httpErrorCode, String errMsg) {
}
