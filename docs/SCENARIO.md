# Refund authorization scenario

A fictional payment service stores a completed USD 100.00 payment owned by customer-1.
Customer-2 is authenticated but does not own that payment. The service accepts a refund
request and returns a typed result. No real payment is issued.

## Implemented boundaries

- The test fixture supplies a java.security.Principal representing trusted upstream identity.
- RefundService receives that principal and looks up a seeded payment in an in-memory map.
- The service validates the request and updates local refund state atomically.
- The service has one intentionally missing object-authorization control, REFUND-01.

Authentication is outside this exercise. A Principal constructed in a test represents that
boundary; it is not safe to construct it from arbitrary request headers in a real application.
No gateway, identity provider, external payment API, or database integration is implemented.

## Business rules

1. Only the authenticated payment owner can perform a full refund.
2. There is no administrator override in this scenario. An admin-looking name grants no access.
3. Missing identity is rejected. Unknown payment IDs return NOT_FOUND.
4. The amount must be positive, match the original amount, and have at most two decimal places.
5. The currency must match USD. Partial refunds are outside the scenario.
6. A completed refund cannot be performed again. Rejections must not consume the owner's refund.
7. Concurrent requests must produce exactly one REFUNDED outcome.

## Working scope

Modify only `src/main/java/com/securelabs/lab/RefundService.java` during the required fix.
Read its tests, but do not change them. Preserve existing legitimate behavior and public methods.
Use finding REFUND-01 and threat T-REFUND-01 in all evidence artifacts.

## Evidence to collect

- Source location and explanation of the trust-boundary violation.
- Actual failed security acceptance checks before the patch.
- A small diagram with implemented and assumed components labelled.
- A change plan, implementation diff, and full passing acceptance output after the patch.
- A fresh review that states what was verified and what remains outside scope.

Optional risk discussion includes payment-existence disclosure through NOT_FOUND, database
transaction boundaries, multiple service instances, persistent idempotency, and authenticated
HTTP integration. The single-process fixture does not prove these properties.
