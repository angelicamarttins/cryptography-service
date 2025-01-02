package com.service.cryptography.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transfer {

  public Transfer(String userDocument, String creditCardToken, Long value) {
    this.userDocument = userDocument;
    this.creditCardToken = creditCardToken;
    this.value = value;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transferId;
  private String userDocument;
  private String creditCardToken;
  private Long value;

}
