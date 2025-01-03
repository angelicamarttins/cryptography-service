package com.service.cryptography.service;

import com.service.cryptography.contract.TransferStrategy;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TransferService {

  private final Map<String, TransferStrategy> strategies;

  public TransferDto processTransfer(TransferPayload transferPayload){

    log.info(transferPayload.getCryptographyMethod().toString());
    log.info(strategies.toString());
    return transferPayload.getTransferDto();
  }

}
