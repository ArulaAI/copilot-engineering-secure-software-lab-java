---
stage: 3-fix
scope_agreed: ""
files_changed: []
evidence: [outputs/findings.md, outputs/threat-model.md, outputs/tasks.md]
gate: ""
---

# Change plan and execution record

Append a section for each selected task and cycle; preserve earlier scope and results.
Scope agreement describes the current task and must be recorded before source changes.

## Task {{selected ID}}: {{objective}}

- Original findings and threat: {{IDs and source evidence}}
- Root cause and exploit preconditions: {{input to sink and missing control}}
- Allowed source files, helpers and new tests: {{exact scope}}
- Legitimate behavior to preserve: {{behavior}}
- Deferred variants and limits: {{scope}}
- Learner agreement: {{actual decision, or pending}}
- Stage/task entry state: {{base SHA, pre-existing status/diff, new files}}

### Acceptance criteria

| Criterion | Positive / negative behavior | Test or inspection | Before fix | After fix | Evidence |
| --- | --- | --- | --- | --- | --- |
| {{criterion}} | {{behavior}} | {{named check}} | {{observed or not run}} | {{result or pending}} | {{path}} |

### Runs and changes

| Task / cycle | Command | Exit | Tests pass/fail/error/skip | Source identity | Full evidence |
| --- | --- | --- | --- | --- | --- |
| {{task / cycle}} | {{actual command}} | {{exit}} | {{actual counts and names}} | {{base, diff and relevant file hashes}} | {{file}} |

## Gate

- [ ] Scope was agreed before edits and actual source/test changes stay within it.
- [ ] Evidence covers security criteria, legitimate behavior and previous-task regression.
- [ ] Task status and cycles match the ledger and independent critique.
- [ ] Unstarted, deferred and unresolved work remains visible. Create does not self-certify.
