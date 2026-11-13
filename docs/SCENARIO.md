# Lab scenario

You inherit a synthetic legacy Java service. Map it, investigate weaknesses and use STRIDE and blast
radius to prioritize five threats. The guide defines five named implementation/review assignments;
the facilitator chooses what fits the 60-minute hands-on session. No prescribed number must be fixed.

## Implementation

Send a named task's implementation prompt to Secure Remediation Engineer. The prompt authorizes
in-scope work: briefly explain, fix the code, add focused security/legitimate-behavior tests, run them
and ordinary regression, summarize and stop. Do not create a plan, task ledger or cycle-history files.
Ask only if a genuine missing requirement blocks the fix. No repeated stage-gate approval.

Send the review prompt in a fresh Secure Code Reviewer chat. It inspects actual code/new tests,
runs focused checks/regression, and gives a concise verdict with one short outputs/review.md entry.
Correct concrete gaps and review again. Three attempts is a facilitator teaching limit, not a software
counter. Do not automatically start another priority, invoke other agents or require formal deferral.

No POM changes, new packages, external requests or service startup. Use existing Java/Maven/JUnit,
Spring Security and H2 with local fixtures. Preserve assertions. Existing Jackson incompatibility may
block full Spring integration; record the limitation once, test local boundaries and never claim an
integration pass that did not happen. Dependency vulnerabilities are not fixed by application edits.

## Tests and results

- `mvn clean test`: ordinary regression; seeded opt-in checks remain explicitly skipped.
- `mvn -Dtest=<actual-task-test> test`: focused tests selected from the requested criteria.
- `mvn -Dsecurity.verify=true -Dtest=<relevant-security-class> test`: existing opt-in checks where relevant.
- `git status --short` and the actual source/test diff: inspect scope, including new files.

The placeholders above are selected by the agent, not typed literally. Use actual command output and
existing test reports; no mandatory per-cycle logging/hashing. Do not repeat unchanged passing runs
or run artifact validators. Unselected seeded failures/skips remain open and do not block another
completed task's acceptance. Relevant missing tests cannot prove a fix.

Final review compares actual completed changes with original findings and tests, retaining open work.
Generic /hand-off captures available context whenever requested, with no gate or plan prerequisite.
