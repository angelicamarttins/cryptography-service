package com.service.cryptography.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transfer {

  private Long transferId;
  private String userDocument;
  private String creditCardToken;
  private Long value;

}
