package com.service.cryptography.service;

import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AesService {

  private final TransferRepository transferRepository;

  public TransferDto createTransfer(TransferDto transferDto) {
    log.info("Saving transfer");

    Transfer savedTransfer = transferRepository.save(TransferDto.fromDtoToEntity(transferDto));

    return TransferDto.fromEntityToDto(savedTransfer);
  }
}
