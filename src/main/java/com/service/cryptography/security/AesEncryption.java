package com.service.cryptography.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AesEncryption {

  public static String encrypt(String plainText, String password)
    throws NoSuchAlgorithmException,
           NoSuchPaddingException,
           InvalidKeyException,
           IllegalBlockSizeException,
           BadPaddingException,
           InvalidKeySpecException {
    log.info("Encrypting plain text");

    SecretKey secretKey = KeyGenerationUtils.generateSecretKeyFromPassword(password);
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String encryptedText, String password)
    throws NoSuchAlgorithmException,
           NoSuchPaddingException,
           InvalidKeyException,
           IllegalBlockSizeException,
           BadPaddingException,
           InvalidKeySpecException {
    SecretKey secretKey = KeyGenerationUtils.generateSecretKeyFromPassword(password);
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
    byte[] decryptedBytes = cipher.doFinal(decodedBytes);

    return new String(decryptedBytes);
  }
}
