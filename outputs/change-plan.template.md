---
stage: 3-fix
fixes: [SQLI-01, PATH-01]
scope_agreed: ""          # yes | no; yes only after the learner agreed in chat
files_changed: []         # files in the actual diff
acceptance: ""            # pass | fail, from the after-fix summary line
evidence: [outputs/findings.md, outputs/threat-model.md]
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

| Fix | Criterion | Proving test | Before fix | After fix |
| --- | --- | --- | --- | --- |
| SQLI-01 | {{criterion}} | {{test name}} | {{fail}} | {{pass / fail}} |
| PATH-01 | {{criterion}} | {{test name}} | {{fail}} | {{pass / fail}} |

## Runs

Copy each summary line exactly as Maven or git printed it.

| Run | Command | Summary line as printed | Failing tests |
| --- | --- | --- | --- |
| Before fix | Reproduce command from docs/SCENARIO.md | {{Tests run: 6, Failures: 6, ...}} | {{names}} |
| After fix | Acceptance command from docs/SCENARIO.md | {{Tests run: 10, Failures: 0, ...}} | {{none or names}} |
| Changed files | `git diff --stat -- src/main/java` | {{files and line counts}} | |

## Gate

- [ ] The learner agreed the scope before the first edit.
- [ ] The diff touches only UserRepository.java and FileHandler.java; no test or pom.xml changed.
- [ ] The after-fix summary line shows every test passing with none skipped.
