package com.securelabs;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Checks that the learner's stage reports are complete forms. It checks form, not truth:
 * header values are allowed values, no {{placeholder}} is left, and cited evidence files exist.
 * The class name does not end in Test, so the normal build never runs it. Run it by name:
 * mvn -l outputs/evidence/forms.txt -Dtest=OutputsCheck test
 * A report that has not been started shows as skipped, not failed.
 */
class OutputsCheck {
    private static final Path OUTPUTS = Path.of("outputs");
    private static final Set<String> GATE = Set.of("pass", "fail");

    private record Rule(String key, Set<String> allowed, boolean number) {
        static Rule any(String key) { return new Rule(key, Set.of(), false); }
        static Rule number(String key) { return new Rule(key, Set.of(), true); }
        static Rule oneOf(String key, Set<String> allowed) { return new Rule(key, allowed, false); }
    }

    /** Report file, the header key whose value shows the report was started, and its rules. */
    private record Report(String file, String startedKey, List<Rule> rules) { }

    private static final List<Report> REPORTS = List.of(
        new Report("codebase-map.md", "packages_mapped", List.of(
            Rule.number("packages_mapped"), Rule.number("entry_points"), Rule.oneOf("gate", GATE))),
        new Report("findings.md", "findings_total", List.of(
            Rule.number("findings_total"),
            Rule.oneOf("top_severity", Set.of("Critical", "High", "Medium", "Low")),
            Rule.oneOf("gate", GATE))),
        new Report("threat-model.md", "threats_total", List.of(
            Rule.number("threats_total"), Rule.any("top5"), Rule.oneOf("gate", GATE))),
        new Report("change-plan.md", "scope_agreed", List.of(
            Rule.oneOf("scope_agreed", Set.of("yes", "no")), Rule.any("files_changed"),
            Rule.oneOf("acceptance", Set.of("pass", "fail")), Rule.oneOf("gate", GATE))),
        new Report("review.md", "verdict", List.of(
            Rule.oneOf("fixes_verified", Set.of("yes", "no")),
            Rule.oneOf("verdict", Set.of("ACCEPTED", "CHANGES REQUIRED", "NEEDS EVIDENCE")),
            Rule.oneOf("correction", Set.of("0", "1")), Rule.oneOf("gate", GATE))),
        new Report("handoff.md", "last_stage", List.of(
            Rule.oneOf("last_stage", Set.of("1a-map", "1b-analyze", "2-threat-model", "3-fix", "4-review")))));

    @TestFactory
    Stream<DynamicTest> stageReportsAreCompleteForms() {
        return REPORTS.stream().map(report -> DynamicTest.dynamicTest(report.file(), () -> {
            Path file = OUTPUTS.resolve(report.file());
            assumeTrue(Files.exists(file), report.file() + " not started (file missing)");
            String text = Files.readString(file);
            Map<String, String> header = header(text);
            String started = header.getOrDefault(report.startedKey(), "");
            assumeTrue(!started.isEmpty() && !started.equals("[]"), report.file() + " not started");

            List<String> problems = new ArrayList<>();
            for (Rule rule : report.rules()) {
                String value = header.getOrDefault(rule.key(), "");
                if (value.isEmpty() || value.equals("[]")) {
                    problems.add(rule.key() + " is empty");
                } else if (rule.number() && !value.matches("\\d+")) {
                    problems.add(rule.key() + " must be a number, found '" + value + "'");
                } else if (!rule.allowed().isEmpty() && !rule.allowed().contains(value)) {
                    problems.add(rule.key() + " is '" + value + "', allowed " + rule.allowed());
                }
            }
            if (report.file().equals("threat-model.md") && !list(header.getOrDefault("top5", "")).isEmpty()
                    && list(header.get("top5")).size() != 5) {
                problems.add("top5 must list exactly five threat IDs");
            }
            if (text.contains("{{")) {
                problems.add("unfilled {{placeholder}} left in the report");
            }
            for (String evidence : list(header.getOrDefault("evidence", ""))) {
                if (!Files.exists(Path.of(evidence))) {
                    problems.add("cited evidence file missing: " + evidence);
                }
            }
            assertTrue(problems.isEmpty(), report.file() + ": " + String.join("; ", problems));
        }));
    }

    /** Reads simple "key: value" lines from the YAML header between the first two --- lines. */
    static Map<String, String> header(String text) {
        Map<String, String> values = new LinkedHashMap<>();
        String[] lines = text.split("\\R");
        if (lines.length == 0 || !lines[0].trim().equals("---")) {
            return values;
        }
        for (int i = 1; i < lines.length && !lines[i].trim().equals("---"); i++) {
            String line = lines[i];
            int hash = line.indexOf(" #");
            if (hash >= 0) {
                line = line.substring(0, hash);
            }
            int colon = line.indexOf(':');
            if (colon > 0) {
                String value = line.substring(colon + 1).trim();
                if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1).trim();
                }
                values.put(line.substring(0, colon).trim(), value);
            }
        }
        return values;
    }

    static List<String> list(String value) {
        String inner = value.trim();
        if (inner.startsWith("[") && inner.endsWith("]")) {
            inner = inner.substring(1, inner.length() - 1);
        }
        List<String> items = new ArrayList<>();
        for (String item : inner.split(",")) {
            String trimmed = item.trim();
            if (!trimmed.isEmpty()) {
                items.add(trimmed);
            }
        }
        return items;
    }
}
