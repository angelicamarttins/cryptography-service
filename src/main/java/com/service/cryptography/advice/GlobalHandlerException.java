package com.service.cryptography.advice;

import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.exception.common.GeneralHttpException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalHandlerException {

  @ExceptionHandler(GeneralHttpException.class)
  public ResponseEntity<ErrorData> handleGeneralHttpException(GeneralHttpException generalHttpException) {
    return new ResponseEntity<>(
      generalHttpException.getErrorData(),
      generalHttpException.getHttpStatus()
    );
  }

}
