package com.service.cryptography.exception.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorData(String errorReason, HttpStatus httpStatus) {
}
