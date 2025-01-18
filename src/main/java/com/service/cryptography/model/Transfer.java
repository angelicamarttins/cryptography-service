package com.service.cryptography.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Transfer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transfer_id", unique = true)
  private Long transferId;
  @Column(name = "user_document", nullable = false)
  private String userDocument;
  @Column(name = "credit_card_token", nullable = false)
  private String creditCardToken;
  @Column(name = "transfer_value", nullable = false)
  private BigDecimal value;

  public Transfer(String userDocument, String creditCardToken, BigDecimal value) {
    this.userDocument = userDocument;
    this.creditCardToken = creditCardToken;
    this.value = value;
  }

}
