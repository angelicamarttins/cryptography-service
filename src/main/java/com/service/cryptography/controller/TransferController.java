package com.service.cryptography.controller;

import com.service.cryptography.model.Transfer;
import com.service.cryptography.model.dto.TransferDto;
import com.service.cryptography.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
  public ResponseEntity<Void> saveCryptography(@RequestBody TransferDto transferDto) throws URISyntaxException {
    log.info("Starting save transfer");

    Transfer savedTransfer = transferRepository.save(TransferDto.fromDtoToEntity(transferDto));

    URI uri = new URI("localhost:8080/" + savedTransfer.getTransferId());

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
