package com.service.cryptography.exception;

import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.exception.common.GeneralHttpException;
import org.springframework.http.HttpStatus;

public class TransferNotFoundException extends GeneralHttpException {

  public TransferNotFoundException(Long transferId) {
    super(new ErrorData("Transfer not found. TransferId: " + transferId, HttpStatus.NOT_FOUND));
  }

}
