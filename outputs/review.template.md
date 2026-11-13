---
stage: 4-review
completed_scope_verified: ""
verdict: ""
evidence: [outputs/findings.md, outputs/threat-model.md, outputs/tasks.md, outputs/task-reviews.md]
gate: ""
---

# Final cumulative review

Review actual changes in a fresh chat. Compare original findings with current source and runs.
ACCEPTED refers to the completed scope; it never implies that deferred tasks or the whole service are secure.

## Original findings to current state

| Original finding | Selected task, if any | Current disposition | Code evidence | Task / regression evidence | Limits / next action |
| --- | --- | --- | --- | --- | --- |
| {{finding}} | {{task or not selected}} | {{verified fixed / partial / open / deferred / unresolved}} | {{file:line or unchanged}} | {{path or missing}} | {{remaining work}} |

## Cumulative checks

| Check | Expected | Actual | Evidence | Result |
| --- | --- | --- | --- | --- |
| Completed task criteria | All agreed criteria met | {{observed}} | {{current runs}} | {{pass/fail/missing}} |
| Interactions and previous fixes | No regression | {{observed}} | {{checks}} | {{pass/fail/missing}} |
| Scope and tests | Only agreed edits, no weakened assertions | {{observed}} | {{full status/diff including untracked}} | {{pass/fail/missing}} |

## Actual runs

| Command | Exit | Named results, counts and skips | Source state | Full evidence |
| --- | --- | --- | --- | --- |
| {{command}} | {{exit}} | {{observed}} | {{base/diff/hashes}} | {{path}} |

## Closeout

- Completed and verified: {{task IDs}}
- Deferred/unstarted: {{task IDs and facilitator scope decision}}
- Partial/unresolved: {{task IDs and concrete gaps}}
- Verdict and basis: {{ACCEPTED / CHANGES REQUIRED / NEEDS EVIDENCE for completed scope}}
- Boundaries not verified: {{limitations}}

## Gate

- [ ] Every original finding has a current disposition with evidence or an explicit unknown.
- [ ] Completed tasks have current acceptance and regression evidence.
- [ ] Deferred tasks are not reported fixed; relevant skipped tests are not counted as passed.
- [ ] The learner inspected closeout and accepted the stated scope and residual risks.
