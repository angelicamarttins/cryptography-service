package com.service.cryptography.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
public class Transfer {

  public Transfer(String userDocument, String creditCardToken, BigDecimal value) {
    this.userDocument = userDocument;
    this.creditCardToken = creditCardToken;
    this.value = value;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transfer_id", unique = true)
  private Long transferId;

  @Column(name = "user_document", unique = true)
  private String userDocument;

  @Column(name = "credit_card_token")
  private String creditCardToken;

  @Column(name = "transfer_value")
  private BigDecimal value;

}
