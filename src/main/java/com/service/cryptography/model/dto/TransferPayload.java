package com.service.cryptography.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cryptography.model.enums.CryptographyMethod;
import lombok.Data;

@Data
public class TransferPayload {

  @JsonProperty("cryptography_method")
  private CryptographyMethod cryptographyMethod;

  @JsonProperty("transfer")
  private TransferDto transferDto;

}
