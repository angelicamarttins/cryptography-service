package com.service.cryptography.model.dto;

import com.service.cryptography.model.Transfer;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDto {

  private Long transferId;

  @NotBlank(groups = TransferDtoValidators.CreateTransfer.class,
    message = "UserDocument is mandatory for creation and optional for updating")
  private String userDocument;

  @NotBlank(groups = TransferDtoValidators.CreateTransfer.class,
    message = "CreditCardToken is mandatory for creation and optional for updating")
  private String creditCardToken;

  @NotNull(groups = TransferDtoValidators.CreateTransfer.class,
    message = "Value is mandatory for creation and optional for updating")
  @DecimalMin(groups = {TransferDtoValidators.CreateTransfer.class, TransferDtoValidators.UpdateTransfer.class},
    value = "0",
    message = "Value must be positive")
  @DecimalMax(groups = {TransferDtoValidators.CreateTransfer.class, TransferDtoValidators.UpdateTransfer.class},
    value = "999999999",
    message = "Value exceeds the maximum allowed")
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
