package com.service.cryptography.model.dto;

import com.service.cryptography.model.Transfer;
import lombok.Builder;

@Builder
public class TransferDto {

  private Long transferId;
  private String userDocument;
  private String creditCardToken;
  private Long value;

  public static TransferDto toDto(Transfer transfer) {
    return TransferDto
        .builder()
        .transferId(transfer.getTransferId())
        .userDocument(transfer.getUserDocument())
        .creditCardToken(transfer.getCreditCardToken())
        .value(transfer.getValue())
        .build();
  }

  public static Transfer toEntity(TransferDto transferDto) {
    return new Transfer(
        transferDto.transferId,
        transferDto.userDocument,
        transferDto.creditCardToken,
        transferDto.value
    );
  }

}
