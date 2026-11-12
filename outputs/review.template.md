---
stage: 4-review
fixes_verified: ""        # yes | no, from section A
verdict: ""               # ACCEPTED | CHANGES REQUIRED | NEEDS EVIDENCE
correction: 0             # 0 | 1; at most one correction pass
evidence: [outputs/evidence/fix.diff, outputs/evidence/acceptance.txt, outputs/evidence/redteam.txt]
gate: ""                  # pass | fail
---

# Review

The reviewer runs as a subagent with isolated context. It reads files, not the authoring chat.

## A. Do the fixes fix it?

| Fix | Check | Expected | Actual | Evidence | Pass/Fail |
| --- | --- | --- | --- | --- | --- |
| SQLI-01 | Root cause removed in the changed methods | {{root cause}} | {{what the diff does}} | outputs/evidence/fix.diff | {{pass / fail}} |
| SQLI-01 | Exploit tests now pass | pass | {{result}} | outputs/evidence/acceptance.txt | {{pass / fail}} |
| PATH-01 | Root cause removed in the changed methods | {{root cause}} | {{what the diff does}} | outputs/evidence/fix.diff | {{pass / fail}} |
| PATH-01 | Exploit tests now pass | pass | {{result}} | outputs/evidence/acceptance.txt | {{pass / fail}} |
| Both | Full suite, nothing skipped | all pass | {{totals}} | outputs/evidence/acceptance.txt | {{pass / fail}} |
| Both | Nothing outside agreed scope changed | two files | {{files in diff}} | outputs/evidence/fix.diff | {{pass / fail}} |

Execution: {{tests the reviewer ran itself / logs only inspected}}.

## B. What failed the critic

Fill only if a check in A failed. One correction pass, then one re-verification.

| Failed check | Why it failed | Correction made | Re-verified result | Evidence |
| --- | --- | --- | --- | --- |
| {{check}} | {{reason}} | {{change}} | {{pass / fail}} | {{file}} |

## C. Red-team

Run only after every check in A passes. Attacks go into a separate `*RedTeamCheck` test class.

| # | Fix | Attack | Expected (attacker refused) | Actual | Evidence | Decision |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | {{fix}} | {{attack}} | {{result}} | {{result}} | outputs/evidence/redteam.txt | {{held / fixed / residual risk: reason}} |

## Verdict

| Item | Value |
| --- | --- |
| Verdict | {{ACCEPTED / CHANGES REQUIRED / NEEDS EVIDENCE}} |
| Residual risks | {{red-team items accepted, variants left unfixed}} |
| Not verified | {{what this review did not cover}} |

## Gate

- [ ] Every check in A cites a file; no result comes from the agent's say-so.
- [ ] Red-team ran only after A passed, and every successful attack has a decision.
- [ ] The verdict covers SQLI-01 and PATH-01 only, not the whole application.
