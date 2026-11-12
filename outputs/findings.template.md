---
stage: 1b-analyze
findings_total: ""        # number of findings in the table
top_severity: ""          # Critical | High | Medium | Low
evidence: [outputs/codebase-map.md]
gate: ""                  # pass | fail
---

# Findings

Basis values: **observed** (seen in a cited file or log), **inferred** (reasoned from observed
facts), **assumed** (not shown by this repository). Unknown is a valid answer.

Severity: **Critical** (remote, no privilege, severe impact), **High**, **Medium**, **Low**.
Sort the table by severity, highest first. Related symptoms of one root cause are one finding
with several locations. Status: **confirmed** (the code path is shown) or **needs evidence**
(plausible, but something the code does not show, such as configuration or a caller, decides it).
Rejected candidates go in the Rejected hypotheses table.

## Findings by severity

| ID | Title | Locations (file#method:line) | OWASP 2025 | Severity | Why this severity | Status | Evidence | Basis |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| F-01 | {{title}} | {{locations}} | {{Axx:2025 name}} | {{severity}} | {{exploit path and impact}} | {{confirmed / needs evidence}} | {{file:line}} | {{basis}} |

## Rejected hypotheses

| Candidate | Why rejected | Evidence |
| --- | --- | --- |
| {{plausible but wrong finding}} | {{reason}} | {{file:line}} |

## Coverage

| Item | Value |
| --- | --- |
| Packages analyzed | {{list}} |
| Not analyzed, and why | {{list or none}} |

## Gate

- [ ] Every finding cites a location you opened and checked.
- [ ] Severity is justified by exploit path and impact, not by a comment in the code.
- [ ] Duplicates are merged into one finding with several locations.
- [ ] At least one plausible false positive is rejected with a reason.
