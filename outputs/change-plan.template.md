---
stage: 3-fix
fixes: [SQLI-01, PATH-01]
scope_agreed: ""          # yes | no; yes only after the learner agreed in chat
files_changed: []         # files in the actual diff
acceptance: ""            # pass | fail, read from outputs/evidence/acceptance.txt
evidence: [outputs/evidence/reproduce.txt, outputs/evidence/acceptance.txt, outputs/evidence/fix.diff]
gate: ""                  # pass | fail
---

# Change plan

The fixes are fixed by the lab: see docs/SCENARIO.md. Plan both before editing either.

## Agreed scope

| Fix | Root cause (file:line) | Methods to change | Behavior to preserve | Variants left unfixed |
| --- | --- | --- | --- | --- |
| SQLI-01 | {{root cause}} | {{methods}} | {{legitimate lookups and sorting}} | {{other methods with the same pattern}} |
| PATH-01 | {{root cause}} | {{methods}} | {{normal file names, dot-dot that stays inside}} | {{other methods with the same pattern}} |

Learner's agreement: {{quote or summarize the learner's reply}}

## Acceptance criteria

| Fix | Criterion | Proving test | Before | After | Evidence |
| --- | --- | --- | --- | --- | --- |
| SQLI-01 | {{criterion}} | {{test name}} | {{fail}} | {{pass / fail}} | outputs/evidence/acceptance.txt |
| PATH-01 | {{criterion}} | {{test name}} | {{fail}} | {{pass / fail}} | outputs/evidence/acceptance.txt |

## Runs

| Run | Command | Totals (run / failed / skipped) | Evidence |
| --- | --- | --- | --- |
| Before fix | Reproduce command from docs/SCENARIO.md | {{totals}} | outputs/evidence/reproduce.txt |
| After fix | Acceptance command from docs/SCENARIO.md | {{totals}} | outputs/evidence/acceptance.txt |
| Diff | `git diff --output=outputs/evidence/fix.diff -- src/main/java` | {{files and lines}} | outputs/evidence/fix.diff |

## Gate

- [ ] The learner agreed the scope before the first edit.
- [ ] The diff touches only UserRepository.java and FileHandler.java; no test or pom.xml changed.
- [ ] acceptance.txt shows every test passing with none skipped.
