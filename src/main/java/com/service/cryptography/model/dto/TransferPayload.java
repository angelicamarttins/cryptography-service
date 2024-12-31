package com.service.cryptography.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cryptography.model.enums.CryptographyMethod;
import lombok.Data;

@Data
public class TransferPayload {

  public record Request(
      @JsonProperty("user_document") String userDocument,
      @JsonProperty("credit_card_token") String creditCardToken,
      @JsonProperty("cryptography_method") CryptographyMethod cryptographyMethod,
      Long value
  ) {
  }

  public record Response(
      @JsonProperty("transfer_id") Long transferId
  ) {
  }

}
