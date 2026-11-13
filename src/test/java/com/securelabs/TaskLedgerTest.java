package com.securelabs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskLedgerTest {
    private static String row(String id, String status, int cycle, String evidence, String verdict, String decision) {
        return "| " + id + " | F-1 | " + status + " | " + cycle
            + " | src/example.java | mvn test | " + evidence + " | " + verdict + " | " + decision + " |\n";
    }
    @Test void supportsAnyNumberOfVerifiedTasks() {
        StringBuilder rows = new StringBuilder();
        for (int i = 1; i <= 8; i++) rows.append(row("T-" + i, "verified", 1, "run.txt", "ACCEPTED", "reviewed"));
        assertTrue(TaskLedger.validate(rows.toString(), p -> true).isEmpty());
    }
    @Test void cannotAdvancePastUnverifiedTask() {
        String rows = row("T-1", "in_progress", 1, "-", "-", "-")
            + row("T-2", "verified", 1, "run.txt", "ACCEPTED", "reviewed");
        assertFalse(TaskLedger.validate(rows, p -> true).isEmpty());
    }
    @Test void rejectsFourthCycle() {
        assertFalse(TaskLedger.validate(row("T-1", "unresolved", 4, "run.txt", "CHANGES REQUIRED", "pause"), p -> true).isEmpty());
    }
    @Test void thirdCycleCanPass() {
        assertTrue(TaskLedger.validate(row("T-1", "verified", 3, "run.txt", "ACCEPTED", "reviewed"), p -> true).isEmpty());
    }
    @Test void verifiedNeedsExistingEvidenceAndReviewerAcceptance() {
        assertFalse(TaskLedger.validate(row("T-1", "verified", 1, "missing.txt", "ACCEPTED", "reviewed"), p -> false).isEmpty());
        assertFalse(TaskLedger.validate(row("T-1", "verified", 1, "run.txt", "NEEDS EVIDENCE", "-"), p -> true).isEmpty());
        assertFalse(TaskLedger.validate(row("T-1", "verified", 0, "run.txt", "ACCEPTED", "-"), p -> true).isEmpty());
    }
    @Test void deferNeedsExplicitDecision() {
        assertFalse(TaskLedger.validate(row("T-1", "deferred", 0, "-", "-", "-"), p -> true).isEmpty());
        String rows = row("T-1", "deferred", 0, "-", "-", "facilitator ended live scope")
            + row("T-2", "verified", 1, "run.txt", "ACCEPTED", "reviewed");
        assertTrue(TaskLedger.validate(rows, p -> true).isEmpty());
    }
    @Test void rejectsDuplicateOrMalformedRows() {
        String row = row("T-1", "not_started", 0, "-", "-", "-");
        assertFalse(TaskLedger.validate(row + row, p -> true).isEmpty());
        assertFalse(TaskLedger.validate("| T-1 | F-1 | verified |", p -> true).isEmpty());
    }
    @Test void unresolvedTaskStopsSubsequentWork() {
        String rows = row("T-1", "unresolved", 3, "run.txt", "CHANGES REQUIRED", "pause")
            + row("T-2", "in_progress", 1, "-", "-", "-");
        assertFalse(TaskLedger.validate(rows, p -> true).isEmpty());
    }
    @Test void historyMustMatchAllPersistedCycles() {
        String ledger = row("T-1", "verified", 2, "run.txt", "ACCEPTED", "reviewed");
        assertFalse(TaskLedger.validateHistory(ledger).isEmpty());
        String history = "## Cycle history\n| T-1 | 1 | base | agreed | critique |\n| T-1 | 2 | diff | corrected | pass |\n";
        assertTrue(TaskLedger.validateHistory(ledger + history).isEmpty());
        assertFalse(TaskLedger.validateHistory(ledger + history + "| T-1 | 2 | diff | corrected | pass |\n").isEmpty());
        assertFalse(TaskLedger.validateHistory(row("T-1", "verified", 1, "run.txt", "ACCEPTED", "reviewed") + history).isEmpty());
        assertFalse(TaskLedger.validateHistory(ledger + "## Cycle history\n| OTHER | 1 | base | agreed | pass |\n").isEmpty());
    }
}
