# Lean release verification

Use existing approved Java/Maven and a disposable checkout for corrected fixtures. No new packages,
POM changes, scanner, service startup or external requests. Preserve distributed starter vulnerabilities.

| Check | Command | Current expected starter result |
| --- | --- | --- |
| Ordinary regression | `mvn clean test` | 16 discovered, ten passed, six intentionally skipped |
| Existing exploit reproduction | `mvn -Dsecurity.verify=true "-Dtest=*SecurityTest" test` | Six assertion failures, no errors/skips |
| File behavior | `mvn -Dtest=FileHandlerTest test` | Eight tests pass using temporary directories |
| Optional completed-report diagnostic, not a Stage 3 step | `mvn -Dtest=OutputsCheck test` | Five checks skipped if generated reports are absent |

The nine retired ledger tests no longer run. Existing generated ledger/plan files do not participate
in form checks or task acceptance. Pending analysis gates may fail optional completion diagnostics,
but do not trigger a Stage 3 repair loop. No form result proves security or changes a test verdict.

Create and Critique use the requested task's actual criteria and focused tests plus ordinary regression.
New tests change counts. Relevant skipped/unrun checks cannot prove acceptance; unrelated unselected
seeded flaws remain open. No requirement to fix every priority before accepting completed scope.
Reviewer executes checks independently and records a concise verdict in review.md, without plan/ledger
or repeated metadata validation. Three attempts is facilitation guidance, not persistent machinery.

In a disposable copy verify corrected query/path fixtures with all relevant checks enabled. A fix that
rejects all listing/deletion must fail positive behavior tests. Do not apply solutions to the starter.

Authoring runs use Maven 3.9.12 and JDK 21.0.6 with existing offline cache. Native IDE behavior, target
Java 17 execution and 60-minute timing remain separate rehearsal checks. No measured speed/usage gain
is claimed. Existing dependency/runtime issues remain explicit if full Spring integration is blocked.

Only the single mapper is used; seven profiles and seven explicit skills remain. Validate YAML, active
links, generic handoff, whole-service discovery and scenario-based modeling. Confirm no agent creates
or repairs tasks/plan/cycle artifacts or auto-starts another task/agent. Leave generated rehearsal
artifacts and participant work untouched. See WORKFLOW_REHEARSAL.md for acceptance observations.
