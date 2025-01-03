package com.service.cryptography.contract;

import com.service.cryptography.model.dto.TransferDto;

public interface TransferStrategy {

  TransferDto createTransfer(TransferDto transferDto);

}
