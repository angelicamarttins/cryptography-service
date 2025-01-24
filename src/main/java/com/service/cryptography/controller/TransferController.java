package com.service.cryptography.controller;

import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.model.dto.TransferDtoValidators;
import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import com.service.cryptography.service.TransferService;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Data
@AllArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

  private final TransferRepository transferRepository;
  private final TransferService transferService;

  @GetMapping("/{transferId}")
  public ResponseEntity<TransferDto> getCryptography(@PathVariable Long transferId, @RequestParam String password) {
    log.info("Starting get transfer");

    TransferDto transfer = transferService.findTransfer(transferId, password);

    return ResponseEntity.ok(transfer);
  }

  @PostMapping
  public ResponseEntity<Void> saveCryptography(
    @Validated(TransferDtoValidators.CreateTransfer.class)
    @RequestBody TransferPayload transferPayload
  )
    throws URISyntaxException {
    log.info("Starting save transfer");
    Long savedTransferId = transferService.processTransfer(transferPayload);

    URI uri = new URI("localhost:8080/transfer/" + savedTransferId);

    log.info("Transfer completed successfully");

    return ResponseEntity.created(uri).build();
  }

  @PatchMapping("/{transferId}")
  public ResponseEntity<String> updateCryptography(@PathVariable Long transferId, @RequestParam String password) {
    log.info("Starting update transfer");

    transferService.updateTransfer(transferId, password);

    return ResponseEntity.ok("Updated cryptography id = " + transferId);
  }

  @DeleteMapping("{transferId}")
  public ResponseEntity<String> deleteCryptography(@PathVariable Long transferId) {
    log.info("Starting delete transfer");

    return ResponseEntity.ok("Deleted cryptography id = " + transferId);
  }

}
