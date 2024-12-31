package com.service.cryptography.controller;

import com.service.cryptography.model.dto.TransferPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

  @GetMapping("/{transferId}")
  public ResponseEntity<String> getCryptography(@PathVariable Long transferId) {
    log.info("Starting get transfer");

    return ResponseEntity.ok("Found cryptography id = " + transferId);
  }

  @PostMapping
  public ResponseEntity<TransferPayload.Response> saveCryptography(@RequestBody TransferPayload.Request request) {
    log.info("Starting save transfer");

    TransferPayload transferPayload = new TransferPayload();

    return ResponseEntity.ok(new TransferPayload.Response());
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
