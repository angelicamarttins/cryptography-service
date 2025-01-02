package com.service.cryptography.model.dto;

import com.service.cryptography.model.Transfer;
import lombok.Builder;

@Builder
public class TransferDto {

  private Long transferId;
  private String userDocument;
  private String creditCardToken;
  private Long value;

  public static TransferDto fromEntityToDto(Transfer transfer) {
    return TransferDto
        .builder()
        .transferId(transfer.getTransferId())
        .userDocument(transfer.getUserDocument())
        .creditCardToken(transfer.getCreditCardToken())
        .value(transfer.getValue())
        .build();
  }

  public static Transfer fromDtoToEntity(TransferDto transferDto) {
    return new Transfer(
        transferDto.userDocument,
        transferDto.creditCardToken,
        transferDto.value
    );
  }

}
