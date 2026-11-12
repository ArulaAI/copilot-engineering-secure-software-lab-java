package com.securelabs.lab;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;

/** Verifies raw webhook bytes represented as UTF-8 text. Event deduplication is a separate control. */
public final class WebhookVerifier {
    private static final Duration ALLOWED_SKEW = Duration.ofMinutes(5);
    private final byte[] secret;
    private final Clock clock;

    public WebhookVerifier(byte[] secret, Clock clock) {
        if (secret == null || secret.length < 32 || clock == null) {
            throw new IllegalArgumentException("A signing key of at least 32 bytes and clock are required");
        }
        this.secret = secret.clone();
        this.clock = clock;
    }

    public boolean verify(String payload, String timestamp, String signature) {
        if (payload == null || timestamp == null || signature == null
                || !signature.matches("[0-9a-fA-F]{64}")) {
            return false;
        }
        try {
            Instant sentAt = Instant.ofEpochMilli(Long.parseLong(timestamp));
            Duration difference = Duration.between(sentAt, clock.instant()).abs();
            if (difference.compareTo(ALLOWED_SKEW) > 0) {
                return false;
            }
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret, "HmacSHA256"));
            byte[] expected = mac.doFinal((timestamp + "." + payload).getBytes(StandardCharsets.UTF_8));
            return MessageDigest.isEqual(expected, HexFormat.of().parseHex(signature));
        } catch (NumberFormatException exception) {
            return false;
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException("Webhook verification is unavailable", exception);
        }
    }
}
