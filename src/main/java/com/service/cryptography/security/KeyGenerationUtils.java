package com.service.cryptography.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerationUtils {

  public static SecretKey generateSecretKeyFromPassword(String password) {
    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), generateSalt(), 65536, 128)

    return new SecretKeySpec();
  }

}
