package com.service.cryptography.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class KeyGenerationUtils {

  public static SecretKey generateSecretKeyFromPassword(String userDocument, String password)
      throws NoSuchAlgorithmException,
      InvalidKeySpecException {
    byte[] salt = generateDeterministicSalt(userDocument, password);
    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    byte[] keyBytes = factory.generateSecret(spec).getEncoded();

    return new SecretKeySpec(keyBytes, "AES");
  }

  private static byte[] generateDeterministicSalt(String userDocument, String password) {
    String data = userDocument + ":" + password;
    return data.getBytes(StandardCharsets.UTF_8);
  }

}
