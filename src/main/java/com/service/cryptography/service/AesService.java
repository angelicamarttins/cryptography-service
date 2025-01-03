package com.service.cryptography.service;

import com.service.cryptography.contract.StrategyType;
import com.service.cryptography.contract.TransferStrategy;
import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.enums.CryptographyMethod;
import com.service.cryptography.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@StrategyType(CryptographyMethod.AES)
public class AesService implements TransferStrategy {

  private final TransferRepository transferRepository;

  @Override
  public TransferDto createTransfer(TransferDto transferDto) {
    log.info("Saving transfer");

    Transfer savedTransfer = transferRepository.save(TransferDto.fromDtoToEntity(transferDto));

    return TransferDto.fromEntityToDto(savedTransfer);
  }
}
