package com.service.cryptography.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cryptography.model.enums.CryptographyMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferPayload {

  @NotNull(groups = TransferDtoValidators.CreateTransfer.class,
    message = "CryptographyMethod is mandatory for creation")
  private CryptographyMethod cryptographyMethod;

  @NotBlank(groups = TransferDtoValidators.CreateTransfer.class, message = "Password is mandatory for creation")
  private String password;

  @NotNull(groups = {TransferDtoValidators.CreateTransfer.class, TransferDtoValidators.UpdateTransfer.class},
    message = "Transfer is mandatory")
  @Valid
  @JsonProperty("transfer")
  private TransferDto transferDto;

}
