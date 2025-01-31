package com.service.cryptography.service;

import static com.service.cryptography.factory.TransferPayloadFactory.createTransferPayload;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import com.service.cryptography.security.AesEncryption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTest {

  @Mock
  TransferRepository transferRepository;

  @InjectMocks
  TransferService transferService;

  @Test
  @DisplayName("When create, process transfer correctly")
  void processTransferCorrectly() {
    Transfer transfer = new Transfer();
    transfer.setTransferId(1L);
    TransferPayload transferPayload = createTransferPayload("password");
    TransferDto transferDto = transferPayload.getTransferDto();

    when(transferRepository.save(any())).thenReturn(transfer);

    try (MockedStatic<AesEncryption> aesMock = mockStatic(AesEncryption.class);
         MockedStatic<TransferDto> transferDtoMock = mockStatic(TransferDto.class)) {
      aesMock.when(() -> AesEncryption.encrypt(eq(transferDto.getUserDocument()), eq(transferPayload.getPassword())))
        .thenReturn(transferDto.getUserDocument());

      aesMock.when(() -> AesEncryption.encrypt(eq(transferDto.getCreditCardToken()), eq(transferPayload.getPassword())))
        .thenReturn(transferDto.getCreditCardToken());

      transferDtoMock.when(() -> TransferDto.fromEncryptedDtoToEntity(
        any(TransferDto.class),
        eq(transferDto.getUserDocument()),
        eq(transferDto.getCreditCardToken())
      )).thenReturn(transfer);

      Long response = transferService.processTransfer(transferPayload);

      assertEquals(1L, response);

      aesMock.verify(
        () -> AesEncryption.encrypt(eq(transferDto.getUserDocument()), eq(transferPayload.getPassword())),
        times(1)
      );
      aesMock.verify(
        () -> AesEncryption.encrypt(eq(transferDto.getCreditCardToken()), eq(transferPayload.getPassword())),
        times(1)
      );

      transferDtoMock.verify(
        () -> TransferDto.fromEncryptedDtoToEntity(
          any(TransferDto.class),
          eq(transferDto.getUserDocument()),
          eq(transferDto.getCreditCardToken())
        ), times(1)
      );
    }


  }
}
