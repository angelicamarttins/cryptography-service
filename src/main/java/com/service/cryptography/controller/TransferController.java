package com.service.cryptography.controller;

import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferPayload;
import com.service.cryptography.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

  private final TransferRepository transferRepository;

  @GetMapping("/{transferId}")
  public ResponseEntity<String> getCryptography(@PathVariable Long transferId) {
    log.info("Starting get transfer");

    return ResponseEntity.ok("Found cryptography id = " + transferId);
  }

  @PostMapping
  public ResponseEntity<TransferPayload.Response> saveCryptography(@RequestBody TransferPayload.Request request) {
    log.info("Starting save transfer");

    Transfer savedTransfer = transferRepository.save();

    return ResponseEntity.ok(new TransferPayload.Response(savedTransfer.getTransferId()));
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
