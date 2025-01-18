package com.service.cryptography.service;

import com.service.cryptography.exception.CryptographyException;
import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import com.service.cryptography.security.AesEncryption;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransferService {

  private final TransferRepository transferRepository;

  public Long processTransfer(TransferPayload transferPayload) {
    log.info("Processing transfer");

    TransferDto transferDto = transferPayload.getTransferDto();
    log.info("transferdto = {}", transferDto);
    try {
      String encryptedUserDocument = AesEncryption.encrypt(
        transferDto.getUserDocument(),
        transferDto.getUserDocument(),
        transferPayload.getPassword()
      );
      String encryptedCreditCardToken = AesEncryption.encrypt(
        transferDto.getCreditCardToken(),
        transferDto.getUserDocument(),
        transferPayload.getPassword()
      );

      Transfer transfer = transferRepository.save(TransferDto.fromEncryptedDtoToEntity(
        transferDto,
        encryptedUserDocument,
        encryptedCreditCardToken
      ));


      throw new IllegalArgumentException("recoreco");
      //      return transfer.getTransferId();
    } catch (Exception exception) {
      log.error(
        "Something went wrong. Error: {} Cause: {}",
        exception.getMessage(),
        exception.getCause()
      );

      throw new CryptographyException(new ErrorData("Erro teste", HttpStatus.BAD_REQUEST), exception.getCause());
    }
  }

}
