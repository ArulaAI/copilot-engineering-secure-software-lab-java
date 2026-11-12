package com.securelabs.lab;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import static org.junit.jupiter.api.Assertions.*;

class RefundServiceTest {
    private final Principal owner = () -> "customer-1";

    static RefundService service() {
        return new RefundService(List.of(new RefundService.Payment(
            "payment-1", "customer-1", new BigDecimal("100.00"), "USD")));
    }

    @Test
    void ownerCanRefundOnce() {
        RefundService service = service();
        assertEquals(RefundService.Result.REFUNDED,
            service.refund(owner, "payment-1", new BigDecimal("100.00"), "USD"));
        assertEquals(RefundService.Result.ALREADY_REFUNDED,
            service.refund(owner, "payment-1", new BigDecimal("100.00"), "USD"));
    }

    @Test
    void unauthenticatedRequestDoesNotConsumeRefund() {
        RefundService service = service();
        assertEquals(RefundService.Result.UNAUTHENTICATED,
            service.refund(null, "payment-1", new BigDecimal("100.00"), "USD"));
        assertEquals(RefundService.Result.UNAUTHENTICATED,
            service.refund(() -> " ", "payment-1", new BigDecimal("100.00"), "USD"));
        assertEquals(RefundService.Result.REFUNDED,
            service.refund(owner, "payment-1", new BigDecimal("100.00"), "USD"));
    }

    @Test
    void invalidRequestsLeavePaymentRefundable() {
        RefundService service = service();
        for (BigDecimal amount : List.of(BigDecimal.ZERO, new BigDecimal("-1.00"),
                new BigDecimal("100.001"), new BigDecimal("101.00"), new BigDecimal("50.00"))) {
            assertEquals(RefundService.Result.INVALID_REQUEST,
                service.refund(owner, "payment-1", amount, "USD"));
        }
        assertEquals(RefundService.Result.INVALID_REQUEST,
            service.refund(owner, "payment-1", null, "USD"));
        assertEquals(RefundService.Result.INVALID_REQUEST,
            service.refund(owner, "payment-1", new BigDecimal("100.00"), "EUR"));
        assertEquals(RefundService.Result.NOT_FOUND,
            service.refund(owner, "unknown", new BigDecimal("100.00"), "USD"));
        assertEquals(RefundService.Result.REFUNDED,
            service.refund(owner, "payment-1", new BigDecimal("100.00"), "USD"));
    }

    @Test
    void concurrentRequestsProduceExactlyOneRefund() throws Exception {
        RefundService service = service();
        var executor = Executors.newFixedThreadPool(2);
        try {
            CyclicBarrier startTogether = new CyclicBarrier(2);
            Callable<RefundService.Result> request = () -> {
                startTogether.await();
                return service.refund(owner, "payment-1", new BigDecimal("100.00"), "USD");
            };
            var results = executor.invokeAll(List.of(request, request));
            assertEquals(1, results.stream().filter(result -> {
                try { return result.get() == RefundService.Result.REFUNDED; }
                catch (Exception exception) { throw new AssertionError(exception); }
            }).count());
        } finally {
            executor.shutdownNow();
        }
    }
}
