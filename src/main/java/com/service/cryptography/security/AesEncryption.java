package com.service.cryptography.security;

import com.service.cryptography.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@AllArgsConstructor
public class AesEncryption {

  private final TransferRepository transferRepository;

  public static String encrypt(String plainText, SecretKey secretKey)
      throws NoSuchAlgorithmException,
      NoSuchPaddingException,
      InvalidKeyException,
      IllegalBlockSizeException,
      BadPaddingException {
    log.info("Encrypting plain text");

    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String encryptedText, SecretKey secretKey)
      throws NoSuchAlgorithmException,
      NoSuchPaddingException,
      InvalidKeyException,
      IllegalBlockSizeException,
      BadPaddingException {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
    byte[] decryptedBytes = cipher.doFinal(decodedBytes);

    return new String(decryptedBytes);
  }
}
