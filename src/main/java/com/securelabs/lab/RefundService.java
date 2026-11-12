package com.securelabs.lab;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Course starter with one intentional object-authorization defect.
 * The principal is supplied by a trusted upstream boundary or the test fixture.
 * This service does not parse bearer tokens or implement authentication.
 */
public final class RefundService {
    public enum Result {
        REFUNDED, UNAUTHENTICATED, FORBIDDEN, INVALID_REQUEST, NOT_FOUND, ALREADY_REFUNDED
    }

    public record Payment(String id, String ownerId, BigDecimal amount, String currency) {
        public Payment {
            if (id == null || id.isBlank() || ownerId == null || ownerId.isBlank()
                    || amount == null || amount.signum() <= 0 || amount.scale() > 2
                    || !"USD".equals(currency)) {
                throw new IllegalArgumentException("Invalid seeded payment");
            }
        }
    }

    private final Map<String, Payment> payments = new HashMap<>();
    private final Set<String> refunded = new HashSet<>();

    public RefundService(Collection<Payment> initialPayments) {
        Objects.requireNonNull(initialPayments, "initialPayments");
        for (Payment payment : initialPayments) {
            if (payments.putIfAbsent(payment.id(), payment) != null) {
                throw new IllegalArgumentException("Duplicate payment ID");
            }
        }
    }

    /** Full refunds only. The monitor makes validation and transition atomic in this fixture. */
    public synchronized Result refund(Principal principal, String paymentId,
                                       BigDecimal amount, String currency) {
        if (principal == null || principal.getName() == null || principal.getName().isBlank()) {
            return Result.UNAUTHENTICATED;
        }
        Payment payment = payments.get(paymentId);
        if (payment == null) {
            return Result.NOT_FOUND;
        }
        // Exercise REFUND-01. Verify whether the authenticated caller may act on this payment.
        if (amount == null || amount.signum() <= 0 || amount.scale() > 2
                || amount.compareTo(payment.amount()) != 0 || !payment.currency().equals(currency)) {
            return Result.INVALID_REQUEST;
        }
        if (refunded.contains(paymentId)) {
            return Result.ALREADY_REFUNDED;
        }
        refunded.add(paymentId);
        return Result.REFUNDED;
    }
}
