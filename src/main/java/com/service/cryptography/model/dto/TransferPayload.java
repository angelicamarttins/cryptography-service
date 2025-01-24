package com.service.cryptography.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cryptography.model.enums.CryptographyMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferPayload {

  @JsonProperty("cryptography_method")
  @NotBlank
  private CryptographyMethod cryptographyMethod;

  @NotBlank
  private String password;

  @JsonProperty("transfer")
  @NotNull
  private TransferDto transferDto;

}
