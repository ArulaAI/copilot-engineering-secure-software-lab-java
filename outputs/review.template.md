---
stage: 4-review
fixes_verified: ""        # yes | no, from section A
verdict: ""               # ACCEPTED | CHANGES REQUIRED | NEEDS EVIDENCE
correction: 0             # 0 | 1; at most one correction pass
evidence: [outputs/change-plan.md]
gate: ""                  # pass | fail
---

# Review

Written in a fresh chat by Secure Code Reviewer. It reads files and runs the tests itself; it never sees the authoring chat.

## A. Do the fixes fix it?

| Fix | Check | Expected | Actual | Evidence | Pass/Fail |
| --- | --- | --- | --- | --- | --- |
| SQLI-01 | Root cause removed in the changed methods | {{root cause}} | {{what the change does}} | {{file:line}} | {{pass / fail}} |
| SQLI-01 | No new vulnerability introduced | none | {{observation}} | {{file:line}} | {{pass / fail}} |
| PATH-01 | Root cause removed in the changed methods | {{root cause}} | {{what the change does}} | {{file:line}} | {{pass / fail}} |
| PATH-01 | No new vulnerability introduced | none | {{observation}} | {{file:line}} | {{pass / fail}} |
| Both | Errors do not leak internal details (exception text, SQL, paths) | no leak | {{observation}} | {{file:line}} | {{pass / fail}} |
| Both | Only UserRepository.java and FileHandler.java changed | two files | {{git diff --stat output}} | git diff | {{pass / fail}} |
| Both | Acceptance rerun by the reviewer | Tests run: 10, Failures: 0, Errors: 0, Skipped: 0 | {{summary line as printed}} | acceptance command | {{pass / fail}} |

## B. What failed the critic

Fill only if a check in A failed. One correction pass, then one re-verification.

| Failed check | Why it failed | Correction made | Re-verified result | Evidence |
| --- | --- | --- | --- | --- |
| {{check}} | {{reason}} | {{change}} | {{pass / fail}} | {{file}} |

## C. Red-team

Run only after every check in A passes. Attacks go into one new `DataRedTeamCheck` test class.

| # | Fix | Attack | Expected (attacker refused) | Actual | Evidence | Decision |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | {{fix}} | {{attack}} | {{result}} | {{result}} | {{red-team summary line or test name}} | {{held / sent back as correction / residual risk: reason}} |

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
