# Release verification

Use the exact release/source state and a disposable checkout for corrected-code and mutation probes.
Do not apply solutions to the distributed starter. Record JDK, Maven, IDE/plugin/harness/model and
all actual outcomes. No installation or external scanner is required.

## Starter and fixture checks

| Check | Command / action | Expected result for this release |
| --- | --- | --- |
| Ordinary starter regression | `mvn clean test` | Exit 0; 25 checks discovered, 19 passed, six explicitly skipped |
| Existing opt-in reproduction | `mvn -Dsecurity.verify=true "-Dtest=*SecurityTest" test` | Exit nonzero; six assertion failures, no errors or skips |
| Workflow ledger regression | `mvn -Dtest=TaskLedgerTest test` | Nine tests pass; cycles, history, evidence and progression cases exercised |
| File behavior | `mvn -Dtest=FileHandlerTest test` | Eight checks pass using temporary directories; read, create, overwrite, list and delete |
| Unstarted report forms | `mvn -Dtest=OutputsCheck test` | Eight report checks skipped when generated reports are absent |
| Dependency boundary | Compare pom.xml with the release base | No changes |

These are fixture counts, not hardcoded acceptance totals for every selected task. Added task tests
change discovery. Inspect named tests and criteria, not a total alone. Starter exploit failures must
be actual security assertions, never missing artifacts or compilation errors.

## Task and final verification

For ANY selected threat, agree source/test scope and positive/negative behavioral criteria. Establish
the flaw locally, persist each Create cycle before editing, then verify in a fresh reviewer chat.
Run task acceptance, legitimate behavior, ordinary regression and prior verified task checks. Use
relevant opt-in fixtures where appropriate; unselected seeded defects remain explicitly open/unverified.
Relevant skipped tests cannot prove acceptance. No fixed issue IDs, file pair or implementation count.

At most three complete Create/critique cycles per task, stopping earlier on acceptance. Preserve cycle
history across chats. A third-cycle failure remains unresolved and pauses for the facilitator. Ending
live scope after one or two completed tasks is supported; the remaining priorities are explicitly
deferred rather than marked fixed. Reopening a task preserves its original budget.

Final review compares ALL original findings and actual changes with the current task ledger and runs.
Verify interactions and previous fixes, include new/untracked test files, and report partial/deferred/
unresolved work. ACCEPTED covers verified completed scope only, never the whole service by implication.

## Corrected-fixture and mutation probes

Facilitator solutions and the known-risk inventory are maintained outside the participant repository
and Copilot workspace. They are not required to execute the participant workflow. In a disposable
copy, verify selected fixes with their actual criteria. The original supplied query/path fixtures can
be corrected and enabled together: this authoring revision discovers 25 passing checks, zero skipped.
This establishes those fixtures only, not remediation of other service risks or HTTP startup.

For path behavior, deliberately replace listing/deletion with unconditional rejection. Ordinary
FileHandlerTest must fail. This protects against deny-everything implementations. Restore corrected
behavior after the probe. Temporary-directory tests must not depend on /var/www/uploads existing or
missing, and exploit probes must never target host secrets or real user files.

## Reports and handoff

- Eight profiles exist: seven visible, Package Explorer hidden. Seven explicit skills exist.
- Profiles and remediation/review skills contain no preset issue IDs, source pair or passing total.
- Stage 2 derives five priorities from all findings; it does not choose a fixed teaching list.
- Each stage writes only its designated artifacts. Only explicit /hand-off with Recorder updates handoff.
- Recorder records the current state whenever asked, in any repository, including pending gates and
  unfinished work; it never refuses for a missing gate. Stage skills check their own readiness.
- Ledger rows preserve task IDs, source scope, cycle, status, tests and evidence; history matches cycles.
- OutputsCheck validates report form and static ledger consistency, not execution, approval or security.
- No completed answer key/solution is present in the working tree. Older Git history is not erased.
- Active Markdown links resolve. Guides, roles, templates, commands and schedule agree.

## Authoring verification record

Executed locally with Maven 3.9.12, JDK 21.0.6 and the existing cache, using offline Maven. POM unchanged.

| Observed check | Result |
| --- | --- |
| New isolated file checks before constructor support | Six assertion failures due to missing upload-root fixture support |
| Starter ordinary regression | 25 discovered; 19 passed, six skipped; exit 0 |
| Starter existing security reproduction | Six assertion failures, zero errors/skips; exit 1 |
| One corrected query task, path flaw still present, scoped checks | Task and selected regression passed; unrelated path exploit checks were not claimed passed |
| Disposable corrected query/path fixtures | 25 passed, zero skipped; exit 0 |
| Reject-all listing/deletion mutation | File behavior suite rejects it: one assertion failure and three unexpected-operation errors |
| Ledger regression | Nine tests pass, including missing/duplicate/reset history and fourth-cycle rejection |

Synthetic report-form fixtures exercise the checker only. A valid fixture must pass all eight checks;
a missing cycle history must fail the task-ledger check. These are not Copilot output or execution
proof for a remediation task. Consuming-agent reasoning checks, if used, are likewise simulated.

This record does not qualify native Copilot behavior or target Java 17 execution. No time, token or
quality improvement has been measured. Complete COMPATIBILITY.md and WORKFLOW_REHEARSAL.md on the
actual supported client route, including a timed complete session, before declaring delivery ready.
