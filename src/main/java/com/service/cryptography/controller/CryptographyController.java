package com.service.cryptography.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/cryptography")
public class CryptographyController {

  @GetMapping("/{cryptographyId}")
  public ResponseEntity<String> getCryptography(@PathVariable Long cryptographyId) {
    log.info("Starting get process");

    return ResponseEntity.ok("Found cryptography id = " + cryptographyId);
  }

  @PostMapping
  public ResponseEntity<String> saveCryptography(@RequestBody String cryptographyType) {
    log.info("Starting save process");

    return ResponseEntity.ok(cryptographyType);
  }

  @PatchMapping("/{cryptographyId}")
  public ResponseEntity<String> updateCryptography(@PathVariable Long cryptographyId) {
    log.info("Starting update process");

    return ResponseEntity.ok("Updated cryptography id = " + cryptographyId);
  }

  @DeleteMapping("{cryptographyId}")
  public ResponseEntity<String> deleteCryptography(@PathVariable Long cryptographyId) {
    log.info("Starting delete process");

    return ResponseEntity.ok("Deleted cryptography id = " + cryptographyId);
  }

}
