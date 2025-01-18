package com.service.cryptography.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralHttpException extends RuntimeException {

  private final ErrorData errorData;
  private final HttpStatus httpStatus;

  public GeneralHttpException(ErrorData errorData, HttpStatus httpStatus, Throwable cause) {
    super(errorData.errorReason(), cause);

    this.errorData = errorData;
    this.httpStatus = httpStatus;
  }

  public GeneralHttpException(ErrorData errorData) {
    this(errorData, errorData.httpStatus(), null);
  }

  public GeneralHttpException(ErrorData errorData, Throwable cause) {
    this(errorData, errorData.httpStatus(), cause);
  }

}
