package com.securelabs.vulnerable.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentHandler {
    private static final Map<String, Transaction> transactions = new HashMap<>();

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
        System.out.println("Processing payment - Card: " + request.getCardNumber() +
                          ", CVV: " + request.getCvv() +
                          ", Amount: " + request.getAmount());

        double amount = request.getAmount();

        String currency = request.getCurrency();

        String cardNumber = request.getCardNumber();

        String transactionId = "txn_" + System.currentTimeMillis();

        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setCardNumber(cardNumber);
        transaction.setCvv(request.getCvv());
        transaction.setUserId(request.getUserId());
        transaction.setStatus("completed");
        transaction.setTimestamp(System.currentTimeMillis());

        transactions.put(transactionId, transaction);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "transactionId", transactionId,
            "amount", amount,
            "cardNumber", cardNumber,
            "status", "completed"
        ));
    }

    @PostMapping("/refund")
    public ResponseEntity<?> processRefund(@RequestBody RefundRequest request) {
        String transactionId = request.getTransactionId();
        double refundAmount = request.getAmount();

        Transaction transaction = transactions.get(transactionId);

        if (transaction == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Transaction not found"));
        }

        System.out.println("Processing refund: " + transactionId + " Amount: " + refundAmount);

        transaction.setStatus("refunded");

        return ResponseEntity.ok(Map.of(
            "success", true,
            "transactionId", transactionId,
            "refundAmount", refundAmount,
            "originalAmount", transaction.getAmount()
        ));
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> handleWebhook(@RequestBody Map<String, Object> payload,
                                           @RequestHeader Map<String, String> headers) {
        System.out.println("Webhook received: " + payload);

        String eventType = (String) payload.get("type");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");

        if ("payment.completed".equals(eventType)) {
            String transactionId = (String) data.get("transactionId");
            Transaction transaction = transactions.get(transactionId);
            if (transaction != null) {
                transaction.setStatus("confirmed");
            }
        }

        return ResponseEntity.ok(Map.of("received", true));
    }

    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactions.get(transactionId);

        if (transaction == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Transaction not found"));
        }

        return ResponseEntity.ok(Map.of(
            "id", transaction.getId(),
            "amount", transaction.getAmount(),
            "currency", transaction.getCurrency(),
            "cardNumber", transaction.getCardNumber(),
            "cvv", transaction.getCvv(),
            "userId", transaction.getUserId(),
            "status", transaction.getStatus()
        ));
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> listTransactions() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            result.add(Map.of(
                "id", t.getId(),
                "amount", t.getAmount(),
                "cardNumber", t.getCardNumber(),
                "userId", t.getUserId(),
                "status", t.getStatus()
            ));
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/transactions/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String transactionId) {
        Transaction removed = transactions.remove(transactionId);

        if (removed == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Transaction not found"));
        }

        return ResponseEntity.ok(Map.of("deleted", true, "transactionId", transactionId));
    }

    // Request/Response classes
    static class PaymentRequest {
        private String cardNumber;
        private String cvv;
        private String expiryDate;
        private double amount;
        private String currency;
        private String userId;

        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
        public String getExpiryDate() { return expiryDate; }
        public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }

    static class RefundRequest {
        private String transactionId;
        private double amount;
        private String reason;

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    static class Transaction {
        private String id;
        private double amount;
        private String currency;
        private String cardNumber;
        private String cvv;
        private String userId;
        private String status;
        private long timestamp;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }
}
