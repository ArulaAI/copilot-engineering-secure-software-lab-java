---
stage: 3-fix
tasks_total: ""
selected_from: outputs/threat-model.md
---

# Task ledger

Initialize from the selected priorities. Any number of rows is supported. Keep priority order.
Statuses: not_started, in_progress, verified, deferred, unresolved. Cycle starts at 0 and is
incremented BEFORE each Create, including the initial attempt, up to 3. Preserve history.
Only the reviewer marks verified from matching acceptance and regression evidence. Only an
explicit facilitator decision defers work. A pending or unresolved earlier task blocks advancement.
Evidence cells contain comma-separated repository-relative files, without Markdown links.
Do not put pipe characters in cells. Use the plan for detailed commands and criteria.

| Task | Finding IDs | Status | Cycle | Scope | Verification | Evidence | Reviewer verdict | Decision |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| {{selected threat ID}} | {{original finding IDs}} | not_started | 0 | {{plan section}} | {{checks}} | - | - | - |

## Cycle history (append only)

| Task | Cycle | Create started at source state | Agreed scope / critique addressed | Result / review evidence |
| --- | --- | --- | --- | --- |
| {{task}} | {{cycle}} | {{base revision and diff/hash snapshot}} | {{scope or specific critique}} | {{pending or review path}} |

The row and cycle history must agree. Form checks cannot enforce edits in Copilot or prove execution.
