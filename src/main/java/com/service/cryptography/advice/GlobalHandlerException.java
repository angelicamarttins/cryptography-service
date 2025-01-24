package com.service.cryptography.advice;

import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.exception.common.GeneralHttpException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
    MethodArgumentNotValidException validationException
  ) {
    Map<String, String> errors = new HashMap<>();
    validationException.getBindingResult()
      .getFieldErrors()
      .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }


}
