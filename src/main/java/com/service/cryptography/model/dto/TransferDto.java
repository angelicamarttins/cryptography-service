package com.service.cryptography.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cryptography.model.Transfer;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class TransferDto {

  @JsonProperty("transfer_id")
  private Long transferId;

  @JsonProperty("user_document")
  private String userDocument;

  @JsonProperty("credit_card_token")
  private String creditCardToken;

  @JsonProperty("value")
  private BigDecimal value;

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
        transferDto.transferId,
        transferDto.userDocument,
        transferDto.creditCardToken,
        transferDto.value
    );
  }

}
