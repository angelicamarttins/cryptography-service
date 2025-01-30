package com.service.cryptography.factory;

import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.model.enums.CryptographyMethod;

public class TransferDtoFactory {

  public static TransferPayload createTransferPayload(String password) {
    return new TransferPayload(
      CryptographyMethod.AES,
      password,
      createTransferDto()
    );
  }

}
