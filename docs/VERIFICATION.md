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
in form checks or task acceptance. Legacy gate fields are ignored by optional diagnostics; generated reports need no approval field. No form result proves security or changes a test verdict.

Create and Critique use the requested task's actual criteria and focused tests plus ordinary regression.
New tests change counts. Relevant skipped/unrun checks cannot prove acceptance; unrelated unselected
seeded flaws remain open. No requirement to fix every priority before accepting completed scope.
Task reviewer executes checks independently and records a concise verdict in review.md, without plan/ledger
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

Final deliverable: Stage 4 generates one structured outputs/security-review.md from its template,
with verdict, scope, fixed/open findings, ASVS/control-area gaps and actual build/test results. Stage 3
review.md remains a short per-task input. No duplicate final append, new whole-service scan or metadata
loop is required. The report is scoped evidence, not production/ASVS certification.

Approval-removal authoring checks: ordinary offline Maven regression discovered 16 tests: ten passed,
six skipped, zero failures/errors. A disposable form-only fixture run passed all five OutputsCheck
cases with missing, pending and failed legacy gate fields. These synthetic checks establish validator
behavior, not security findings. Seven Local prompt agent names, seven skill headers, six bundled
template paths, Markdown links and diagram XML were checked. Native Local routing, argument handling
and model behavior still require the timed IDE rehearsal; no native execution is claimed here.
