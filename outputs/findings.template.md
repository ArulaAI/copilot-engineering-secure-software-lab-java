---
stage: 1b-analyze
findings_total: ""
top_severity: ""          # Critical | High | Medium | Low, with qualifications in the rationale
evidence: [outputs/codebase-map.md]
gate: ""                  # pass | fail; pending until the human decides
---

# Original findings

Basis: observed, inferred or assumed. Unknown is valid. Sort findings by justified severity.
Use Web Top 10 2025 from docs/owasp-reference/top-10-summary.md; do not inherit historical IDs from comments.
Keep Severity to Critical, High, Medium or Low and put qualifications in the rationale.
Severity must name impact and prerequisites, not equate every source defect with a remote exploit.
Do not default to Medium solely because a production caller was not found.

## Assessment dimensions

- Code assessment: confirmed defect (implementation violates a supported intended contract or lacks a required control), conditional risk (danger depends on usage/preconditions), needs evidence (insufficient support).
- Reachability: demonstrated handler path, demonstrated internal caller, standalone component, unknown. Handler source is not proof of live network exposure. Record test callers separately.
- Verification: source inspection, test-demonstrated, runtime-demonstrated. Link actual evidence and state who executed it. Reading a test is not executing it.
- Rejected claims go below. Rejecting a claimed HTTP exploit does not automatically reject the underlying component risk.

## Findings by severity

| ID | Title | Locations (file#method:line) | OWASP edition | Severity | Impact, prerequisites and uncertainty | Code assessment | Reachability | Verification | Evidence | Basis |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| {{ID}} | {{title}} | {{locations}} | {{category and edition}} | {{severity}} | {{rationale and conditions}} | {{assessment}} | {{reachability and caller evidence}} | {{verification level}} | {{file:line or run path}} | {{basis}} |

Merge symptoms only when the missing control/root cause is the same; retain all affected locations.

## Rejected hypotheses

| Claim | Why unsupported or rejected | Evidence | Underlying risk retained, if any |
| --- | --- | --- | --- |
| {{claim}} | {{reason}} | {{source}} | {{finding ID or none with explanation}} |

## Whole-service coverage

Inventory every application class and relevant configuration, including standalone components.

| Package / class / configuration | Inputs, contracts and operations reviewed | Finding IDs | No finding / incomplete-review explanation |
| --- | --- | --- | --- |
| {{source path}} | {{review scope}} | {{IDs or none}} | {{reason or remaining gap}} |

## Stage-entry source state

{{Base revision, tracked/untracked changes and inspected content identity. Distinguish supplied evidence from actual execution.}}

## Gate

- [ ] Accepted mapping gate and stage handoff were checked before discovery.
- [ ] Whole-service coverage includes standalone components and configuration, with gaps explicit.
- [ ] Code defects, reachability and verification are assessed separately.
- [ ] Severity is justified with impact and prerequisites; remote exposure is not invented.
- [ ] Findings cite inspected evidence and duplicates share a root cause.
- [ ] Unsupported hypotheses are explained without erasing separately supported risks.
