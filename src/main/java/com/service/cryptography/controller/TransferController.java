package com.service.cryptography.controller;

import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import com.service.cryptography.service.TransferService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@Data
@AllArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

  private final TransferRepository transferRepository;
  private final TransferService transferService;

  @GetMapping("/{transferId}")
  public ResponseEntity<String> getCryptography(@PathVariable Long transferId) {
    log.info("Starting get transfer");

    return ResponseEntity.ok("Found cryptography id = " + transferId);
  }

  @PostMapping
  public ResponseEntity<Void> saveCryptography(@RequestBody TransferPayload transferPayload)
      throws URISyntaxException {
    log.info("Starting save transfer");

    Long savedTransferId = transferService.processTransfer(transferPayload);

    URI uri = new URI("http://localhost:8080/transfer/" + savedTransferId);

    log.info("Transfer completed successfully");

    return ResponseEntity.created(uri).build();
  }

  @PatchMapping("/{transferId}")
  public ResponseEntity<String> updateCryptography(@PathVariable Long transferId) {
    log.info("Starting update transfer");

    return ResponseEntity.ok("Updated cryptography id = " + transferId);
  }

  @DeleteMapping("{transferId}")
  public ResponseEntity<String> deleteCryptography(@PathVariable Long transferId) {
    log.info("Starting delete transfer");

    return ResponseEntity.ok("Deleted cryptography id = " + transferId);
  }

}
