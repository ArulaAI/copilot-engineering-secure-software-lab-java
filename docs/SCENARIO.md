# Lab scenario

Your team has inherited a synthetic legacy Java service with users, payments, files and sessions.
Before extending it, understand the code, identify risks and demonstrate defensible improvements.
Nothing is deployed. Review all actual source packages and configuration; standalone helpers are
not automatically connected to controllers. Do not invent infrastructure to complete a diagram.

## Stage contracts

1. Map and investigate: produce architecture/attack-surface evidence and all supported findings.
2. Apply STRIDE to the service and all findings. Assess severity, impact, likelihood and blast radius.
   Select the top five threats from that evidence. This is an analysis result, not a prescribed list.
3. Follow the named implementation tasks and Create/Critique prompts in LAB_ACTION_GUIDE.md, linking them to the findings and Stage 2 threats. Implement chosen tasks sequentially. The facilitator normally chooses one or two for the live
   session; participants may continue through all priorities as time permits or afterward. There is
   no implementation count restriction. Agree each task's scope and acceptance, Create, then Critique
   in a fresh reviewer chat. Verify the current task before the next. Up to three complete cycles
   per task, stopping earlier when accepted. Third-cycle failure pauses for the facilitator.
4. Review all actual changes against original findings. Accept completed scope only, with explicit
   deferred, unstarted and unresolved tasks. Record residual risks and close the stage.

## Working scope

Any selected threat may require changes to relevant application files, small supporting helpers and
new behavioral tests. Agree these per task. Preserve existing assertions; never weaken or delete tests
to report green. No dependency/POM changes, new installations, external API calls or application startup.
Use the existing Java/Maven, Spring Security, JUnit and H2 facilities with local synthetic fixtures.
A fix can add a trustworthy local identity boundary when needed; do not treat an unchecked user-supplied
ID as authenticated identity. Test the chosen boundary and state its limits.

Known dependency/runtime issues remain findings if not changed; method-level tests do not establish
that the full Spring application starts. Do not assert every dependency risk is fixed by application code.

## Evidence and test selection

| Purpose | Command or action |
| --- | --- |
| Setup and ordinary regression | `mvn clean test` |
| Current tracked changes | `git diff --stat` and `git diff -- src/main/java src/test/java` |
| Include new files | `git status --short`, then inspect each untracked source/test file |
| Identify tested file contents | `git hash-object <relevant-source-and-test-paths>` |
| Selected task tests | `mvn -Dtest=<actual-test-class-or-method> test` |
| Selected opt-in security tests, where relevant | `mvn -Dsecurity.verify=true -Dtest=<actual-security-class> test` |
| Check report forms and task-ledger consistency | `mvn -Dtest=OutputsCheck test` |

Angle-bracket test/path values are selected from actual evidence by the agent, not literal runnable
commands. Save full command output under outputs/evidence/ (Maven's `-l <file>` option is available),
record exit status, counts, failures/skips and current source state. No participant log copying is needed.

The shipped opt-in security fixtures cover only database query handling and upload paths. They are
available checks, not assigned tasks or complete service coverage. Enable only relevant fixtures for
task acceptance; retain skipped/unselected fixtures as explicitly unverified. New task security and
legitimate-behavior tests normally end in Test so they join ordinary regression. Rerun previously
verified task checks too. Do not require all intentionally unfixed defects to pass before verifying an
unrelated task. Final review must still disclose those open defects.

Only /hand-off with Hand-off Recorder updates outputs/handoff.md. It records the current state
whenever asked, including pending decisions, and never approves or blocks progress; each stage
checks its own readiness. Each stage
writes its own report; Stage 3 also maintains the task ledger and critique history. Participants make
scope, gate and residual-risk decisions; agents derive summaries from files and actual tool results.
