package com.service.cryptography.service;

import com.service.cryptography.exception.CryptographyException;
import com.service.cryptography.exception.TransferNotFoundException;
import com.service.cryptography.exception.common.ErrorData;
import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import com.service.cryptography.security.AesEncryption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
        transferPayload.getPassword()
      );
      String encryptedCreditCardToken = AesEncryption.encrypt(
        transferDto.getCreditCardToken(),
        transferPayload.getPassword()
      );

      Transfer transfer = transferRepository.save(TransferDto.fromEncryptedDtoToEntity(
        transferDto,
        encryptedUserDocument,
        encryptedCreditCardToken
      ));

      return transfer.getTransferId();
    } catch (NoSuchPaddingException
             | IllegalBlockSizeException
             | NoSuchAlgorithmException
             | BadPaddingException
             | InvalidKeySpecException
             | InvalidKeyException cryptographyException
    ) {
      log.error(
        "Something went wrong during encryption. Error: {} Cause: {}",
        cryptographyException.getMessage(),
        cryptographyException.getCause()
      );

      throw new CryptographyException(
        new ErrorData(cryptographyException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
        cryptographyException.getCause()
      );
    }
  }

  public TransferDto findTransfer(Long transferId, String password) {
    try {
      Transfer transfer = transferRepository
        .findById(transferId)
        .orElseThrow(() -> new TransferNotFoundException(transferId));

      String decryptedUserDocument = AesEncryption.decrypt(
        transfer.getUserDocument(),
        password
      );
      String decryptedCreditCardToken = AesEncryption.decrypt(
        transfer.getCreditCardToken(),
        password
      );

      return TransferDto.fromEncryptedEntityToDto(transfer, decryptedUserDocument, decryptedCreditCardToken);
    } catch (NoSuchPaddingException
             | IllegalBlockSizeException
             | NoSuchAlgorithmException
             | BadPaddingException
             | InvalidKeySpecException
             | InvalidKeyException cryptographyException
    ) {
      log.error(
        "Something went wrong during decryption. Error: {} Cause: {}",
        cryptographyException.getMessage(),
        cryptographyException.getCause()
      );

      throw new CryptographyException(
        new ErrorData(cryptographyException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
        cryptographyException.getCause()
      );
    }
  }

  public void updateTransfer(Long transferId, String password) {
    try {
      Transfer transfer = transferRepository
        .findById(transferId)
        .orElseThrow(() -> new TransferNotFoundException(transferId));

      String decryptedUserDocument = AesEncryption.decrypt(
        transfer.getUserDocument(),
        password
      );
      String decryptedCreditCardToken = AesEncryption.decrypt(
        transfer.getCreditCardToken(),
        password
      );

    } catch (NoSuchPaddingException
             | IllegalBlockSizeException
             | NoSuchAlgorithmException
             | BadPaddingException
             | InvalidKeySpecException
             | InvalidKeyException cryptographyException
    ) {
      log.error(
        "Something went wrong during decryption. Error: {} Cause: {}",
        cryptographyException.getMessage(),
        cryptographyException.getCause()
      );

      throw new CryptographyException(
        new ErrorData(cryptographyException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
        cryptographyException.getCause()
      );
    }
  }

}
