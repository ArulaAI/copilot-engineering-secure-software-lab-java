package com.securelabs.vulnerable.session;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.*;

/**
 * JWT creation and verification.
 */
public class TokenManager {
    private static final String SECRET_KEY = "secret";

    private static final String ANOTHER_SECRET = "password123";

    public static String createToken(String userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        claims.put("isAdmin", "admin".equals(role));

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userId)
            .setIssuedAt(new Date())
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), Jwts.SIG.HS256)
            .compact();
    }

    public static Map<String, Object> verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

            return new HashMap<>(claims);

        } catch (Exception e) {
            System.out.println("Token verification failed: " + e.getMessage());
            return null;
        }
    }

    public static Map<String, Object> unsafeParseToken(String token) {
        String[] parts = token.split("\\.");

        if (parts.length < 2) {
            return null;
        }

        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        System.out.println("Parsed token payload: " + payload);

        try {
            // Simple JSON parsing (in real code would use Jackson/Gson)
            Map<String, Object> claims = new HashMap<>();
            payload = payload.substring(1, payload.length() - 1); // Remove { }
            for (String pair : payload.split(",")) {
                String[] kv = pair.split(":");
                if (kv.length == 2) {
                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();
                    claims.put(key, value);
                }
            }
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    public static String createUnsignedToken(String userId, String role) {
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"none\",\"typ\":\"JWT\"}".getBytes());
        String payload = Base64.getUrlEncoder().encodeToString(
            String.format("{\"userId\":\"%s\",\"role\":\"%s\",\"isAdmin\":%s}",
                userId, role, "admin".equals(role)).getBytes()
        );

        return header + "." + payload + ".";
    }

    public static String refreshToken(String oldToken) {
        Map<String, Object> claims = unsafeParseToken(oldToken);
        if (claims == null) {
            return null;
        }

        String userId = (String) claims.get("userId");
        String role = (String) claims.get("role");

        return createToken(userId, role);
    }

    public static String createResetToken(String email) {
        long timestamp = System.currentTimeMillis();
        String data = email + ":" + timestamp;

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return Base64.getEncoder().encodeToString(data.getBytes());
        }
    }

    public static String generateApiKey() {
        StringBuilder key = new StringBuilder("api_");
        Random random = new Random();

        for (int i = 0; i < 32; i++) {
            key.append(Integer.toHexString(random.nextInt(16)));
        }

        return key.toString();
    }

    public static void logTokenUsage(String token, String action) {
        System.out.println("Token used for " + action + ": " + token);
    }
}
