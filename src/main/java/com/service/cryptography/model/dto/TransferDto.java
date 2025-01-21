package com.service.cryptography.model.dto;

import com.service.cryptography.model.Transfer;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDto {

  private Long transferId;

  private String userDocument;

  private String creditCardToken;

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

  public static Transfer fromEncryptedDtoToEntity(
    TransferDto transferDto,
    String encryptedUserDocument,
    String encryptedCreditCardToken
  ) {
    return new Transfer(
      transferDto.transferId,
      encryptedUserDocument,
      encryptedCreditCardToken,
      transferDto.value
    );
  }

  public static TransferDto fromEncryptedEntityToDto(
    Transfer transfer,
    String decryptedUserDocument,
    String decryptedCreditCardToken
  ) {
    return TransferDto
      .builder()
      .transferId(transfer.getTransferId())
      .userDocument(decryptedUserDocument)
      .creditCardToken(decryptedCreditCardToken)
      .value(transfer.getValue())
      .build();
  }

}
