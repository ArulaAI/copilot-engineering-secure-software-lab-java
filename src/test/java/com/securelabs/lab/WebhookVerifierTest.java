package com.securelabs.lab;

import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.*;

class WebhookVerifierTest {
    private final Instant now = Instant.ofEpochMilli(1_700_000_000_000L);
    private final Clock clock = Clock.fixed(now, ZoneOffset.UTC);
    private final byte[] key = new byte[32];

    // HMAC-SHA256 vector generated independently using Python's standard hmac library.
    private static final String SIGNATURE = "4caf0758675a8b3f0c972770d377e575a17b55e681ddd42ee0008f6002247684";

    @Test
    void validSignatureAuthenticatesExactRawPayload() {
        WebhookVerifier verifier = new WebhookVerifier(key, clock);
        assertTrue(verifier.verify("{}", "1700000000000", SIGNATURE));
        assertFalse(verifier.verify("{ }", "1700000000000", SIGNATURE));
        assertFalse(verifier.verify("{}", "1700000000001", SIGNATURE));
        assertTrue(new WebhookVerifier(key, Clock.offset(clock, Duration.ofMinutes(5)))
            .verify("{}", "1700000000000", SIGNATURE));
        assertTrue(new WebhookVerifier(key, Clock.offset(clock, Duration.ofMinutes(-5)))
            .verify("{}", "1700000000000", SIGNATURE));
        key[0] = 1;
        assertTrue(verifier.verify("{}", "1700000000000", SIGNATURE));
    }

    @Test
    void configurationFailsClosed() {
        assertThrows(IllegalArgumentException.class, () -> new WebhookVerifier(null, clock));
        assertThrows(IllegalArgumentException.class, () -> new WebhookVerifier(new byte[0], clock));
        assertThrows(IllegalArgumentException.class, () -> new WebhookVerifier(new byte[31], clock));
        assertThrows(IllegalArgumentException.class, () -> new WebhookVerifier(key, null));
    }

    @Test
    void invalidAndExpiredInputsAreRejected() {
        WebhookVerifier verifier = new WebhookVerifier(key, clock);
        for (String signature : new String[] {null, "", "00", "z".repeat(64), "0".repeat(64)}) {
            assertFalse(verifier.verify("{}", "1700000000000", signature));
        }
        for (String timestamp : new String[] {null, "", "bad", "1699999699999",
                "1700000300001", Long.toString(Long.MIN_VALUE), Long.toString(Long.MAX_VALUE)}) {
            assertFalse(verifier.verify("{}", timestamp, SIGNATURE));
        }
        assertFalse(verifier.verify(null, "1700000000000", SIGNATURE));
        assertFalse(new WebhookVerifier(key, Clock.offset(clock, Duration.ofMinutes(5).plusMillis(1)))
            .verify("{}", "1700000000000", SIGNATURE));
        assertFalse(new WebhookVerifier(key, Clock.offset(clock, Duration.ofMinutes(-5).minusMillis(1)))
            .verify("{}", "1700000000000", SIGNATURE));
        // Valid signature for an extreme timestamp proves rejection is caused by the time policy.
        assertFalse(verifier.verify("{}", "-9223372036854775808",
            "797ddc7010611d4ec19f45db93588fe6d81e25076af586c8a48820b6c0923248"));
    }
}
