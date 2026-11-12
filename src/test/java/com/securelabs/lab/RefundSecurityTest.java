package com.securelabs.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/** These acceptance checks intentionally fail on the starter. */
@EnabledIfSystemProperty(named = "lab.verify", matches = "true")
class RefundSecurityTest {
    @Test
    void anotherCustomerCannotRefundAndOwnerStillCan() {
        RefundService service = RefundServiceTest.service();
        assertEquals(RefundService.Result.FORBIDDEN,
            service.refund(() -> "customer-2", "payment-1", new BigDecimal("100.00"), "USD"));
        assertEquals(RefundService.Result.REFUNDED,
            service.refund(() -> "customer-1", "payment-1", new BigDecimal("100.00"), "USD"));
    }

    @Test
    void adminLookingNameGrantsNoPrivilege() {
        RefundService service = RefundServiceTest.service();
        assertEquals(RefundService.Result.FORBIDDEN,
            service.refund(() -> "admin", "payment-1", new BigDecimal("100.00"), "USD"));
    }
}
