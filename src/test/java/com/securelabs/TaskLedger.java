package com.securelabs;

import java.util.*;
import java.util.function.Predicate;

/** Checks ledger consistency, not whether a model really executed or enforced a decision. */
final class TaskLedger {
    private static final Set<String> STATES = Set.of("not_started", "in_progress", "verified", "deferred", "unresolved");
    private TaskLedger() { }

    static List<String> validate(String text, Predicate<String> evidenceExists) {
        List<String> errors = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        boolean earlierUnfinished = false;
        int active = 0;
        for (String line : text.split("\\R")) {
            if (!line.startsWith("|")) continue;
            String[] cells = line.substring(1).split("\\|", -1);
            if (cells.length > 0 && (cells[0].trim().equals("Task") || cells[0].trim().matches("[- :]+"))) continue;
            if (cells.length != 10 || !cells[9].isBlank()) { errors.add("Malformed task row"); continue; }
            for (int i = 0; i < cells.length; i++) cells[i] = cells[i].trim();
            String id = cells[0], status = cells[2];
            if (id.isBlank() || !ids.add(id)) errors.add("Missing or duplicate task ID: " + id);
            if (cells[1].isBlank() || cells[1].equals("-")) errors.add(id + ": finding IDs missing");
            if (!STATES.contains(status)) errors.add(id + ": invalid status");
            int cycle;
            try { cycle = Integer.parseInt(cells[3]); }
            catch (NumberFormatException e) { errors.add(id + ": invalid cycle"); continue; }
            if (cycle < 0 || cycle > 3) errors.add(id + ": cycle must be 0 to 3");
            boolean progressed = Set.of("in_progress", "verified", "unresolved").contains(status);
            if (progressed && cycle == 0) errors.add(id + ": started task needs a cycle");
            if (status.equals("not_started") && cycle != 0) errors.add(id + ": unstarted task has cycles");
            if (progressed && earlierUnfinished) errors.add(id + ": earlier task is not verified or explicitly deferred");
            if (status.equals("in_progress")) active++;
            if (status.equals("deferred") && (cells[8].isBlank() || cells[8].equals("-"))) errors.add(id + ": deferral needs a facilitator decision");
            if (status.equals("verified")) {
                if (!cells[7].equals("ACCEPTED")) errors.add(id + ": reviewer acceptance missing");
                if (cells[4].isBlank() || cells[4].equals("-") || cells[5].isBlank() || cells[5].equals("-")) errors.add(id + ": scope or verification missing");
                if (cells[6].isBlank() || cells[6].equals("-")) errors.add(id + ": run evidence missing");
                else for (String path : cells[6].split(",")) if (!evidenceExists.test(path.trim())) errors.add(id + ": missing evidence " + path.trim());
            }
            if (!Set.of("verified", "deferred").contains(status)) earlierUnfinished = true;
        }
        if (ids.isEmpty()) errors.add("No task rows");
        if (active > 1) errors.add("Only one task may be in progress");
        return errors;
    }
    static List<String> validateHistory(String text) {
        List<String> errors = new ArrayList<>();
        String[] sections = text.split("## Cycle history", 2);
        Map<String, Integer> declared = new LinkedHashMap<>();
        for (String line : sections[0].split("\\R")) {
            if (!line.startsWith("|")) continue;
            String[] c = line.substring(1).split("\\|", -1);
            if (c.length != 10 || c[0].trim().equals("Task") || c[0].trim().matches("[- :]+")) continue;
            try { declared.put(c[0].trim(), Integer.parseInt(c[3].trim())); }
            catch (NumberFormatException e) { errors.add("History cannot match invalid cycle"); }
        }
        Map<String, Integer> seen = new HashMap<>();
        if (sections.length == 2) for (String line : sections[1].split("\\R")) {
            if (!line.startsWith("|")) continue;
            String[] c = line.substring(1).split("\\|", -1);
            if (c[0].trim().equals("Task") || c[0].trim().matches("[- :]+")) continue;
            if (c.length != 6 || !c[5].isBlank()) { errors.add("Malformed cycle history row"); continue; }
            String id = c[0].trim();
            try {
                int cycle = Integer.parseInt(c[1].trim());
                int expected = seen.getOrDefault(id, 0) + 1;
                if (!declared.containsKey(id) || cycle != expected || cycle > declared.getOrDefault(id, 0)) errors.add(id + ": cycle history is reset, duplicate or out of order");
                if (c[2].isBlank() || c[2].trim().equals("-") || c[3].isBlank() || c[3].trim().equals("-")) errors.add(id + ": cycle baseline or agreed scope missing");
                seen.put(id, cycle);
            } catch (NumberFormatException e) { errors.add(id + ": invalid history cycle"); }
        }
        for (var entry : declared.entrySet()) if (seen.getOrDefault(entry.getKey(), 0).intValue() != entry.getValue().intValue()) errors.add(entry.getKey() + ": history does not match current cycle");
        return errors;
    }
}
