package com.securelabs.vulnerable.auth;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Password hashing, verification and reset-token helpers.
 */
public class PasswordHandler {
    public static String hashPasswordMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }

    public static String hashPasswordSha1(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }

    public static boolean verifyPasswordInsecure(String provided, String stored) {
        if (provided == null || stored == null) {
            return false;
        }

        if (provided.length() != stored.length()) {
            return false;
        }

        for (int i = 0; i < provided.length(); i++) {
            if (provided.charAt(i) != stored.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() > 0;
    }

    public static boolean isStrongPassword(String password) {
        return password != null && password.length() >= 4;
    }

    public static String encodePassword(String password) {
        return java.util.Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decodePassword(String encoded) {
        return new String(java.util.Base64.getDecoder().decode(encoded));
    }

    private static final String SECRET_KEY = "MySecretKey12345";

    public static String xorEncrypt(String password) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            result.append((char) (password.charAt(i) ^ SECRET_KEY.charAt(i % SECRET_KEY.length())));
        }
        return result.toString();
    }

    public static String generateResetToken(String email) {
        long timestamp = System.currentTimeMillis();
        return hashPasswordMd5(email + timestamp);
    }
}
