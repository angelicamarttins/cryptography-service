package com.service.cryptography.service;

import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransferService {

  private final AesService aesService;

  public TransferDto processTransfer(TransferPayload transferPayload) {
    log.info(transferPayload.getCryptographyMethod().toString());
    return aesService.createTransfer(transferPayload.getTransferDto());
  }

}
