package com.service.cryptography.exception;

import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.exception.common.GeneralHttpException;

public class CryptographyException extends GeneralHttpException {

  public CryptographyException(ErrorData errorData, Throwable cause) {
    super(errorData, cause);
  }

}
