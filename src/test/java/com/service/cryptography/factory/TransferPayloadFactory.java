package com.service.cryptography.factory;

import static com.service.cryptography.factory.TransferDtoFactory.createTransferDto;

import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.model.enums.CryptographyMethod;

public class TransferPayloadFactory {

  public static TransferPayload createTransferPayload(String password) {
    return new TransferPayload(
      CryptographyMethod.AES,
      password,
      createTransferDto()
    );
  }

}
