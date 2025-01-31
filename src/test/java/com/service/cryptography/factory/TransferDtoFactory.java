package com.service.cryptography.factory;

import com.service.cryptography.model.dto.TransferDto;
import java.math.BigDecimal;

public class TransferDtoFactory {

  public static TransferDto createTransferDto() {
    return TransferDto
      .builder()
      .transferId(1L)
      .userDocument("01234567890")
      .creditCardToken("0123456789")
      .value(BigDecimal.ONE)
      .build();
  }

}
