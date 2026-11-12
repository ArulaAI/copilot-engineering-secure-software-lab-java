# Secure Software learning lab

This repository contains intentionally vulnerable training code. Use synthetic data only.

## Required workflow

Read `docs/SCENARIO.md` and `LAB_ACTION_GUIDE.md`. The required implementation is
`src/main/java/com/securelabs/lab/RefundService.java`. The upstream caller authenticates
identity. Tests supply a trusted Principal fixture. This does not implement HTTP authentication.

- Investigate source before concluding that a finding exists. Cite paths, symbols, and current lines.
- Distinguish observed behavior, inferred risks, assumptions, and untested claims.
- Never use `reference/`, answer keys, or prior generated findings as discovery evidence.
  These are facilitator resources, visible in this public repo, not an access-control boundary.
- A finding count or passing baseline suite cannot establish that a security defect is fixed.
- Preserve the exercise scope, public contract, and legitimate behavior. Do not change tests to obtain green results.
- Facilitator audits must verify reference code independently. Participant work must not consult
  facilitator references. A name such as "secure" does not establish correctness.
- No package additions, dependency upgrades, external services, Python, hooks, CLI installation,
  or Spring application startup are needed for the required exercise.
- Keep implementation edits limited to the selected finding. Explain proposed scope changes first.

## Evidence chain

Use one identifier from finding to threat, change plan, patch, and acceptance result.
Use REFUND-01 for the required scenario. Copy the templates from `outputs/*.template.md`
when requested. Reading agents return reports in chat. The participant saves their output.
Do not claim that a file was written unless the file exists and was checked.

## Commands

- Setup and baseline: `mvn clean test` (optional security acceptance checks are skipped).
- Reproduce the seeded issue: `mvn -Dlab.verify=true -Dtest=RefundSecurityTest test`
  (two assertion failures on the unmodified starter are expected).
- Verify a participant fix: `mvn -Dlab.verify=true clean test` (all checks must pass).
- Existing cached artifacts only: append `-o` if the approved environment supports an offline run.

Record the actual command, exit status, failure names, and interpretation. Report skipped tests.
Do not represent a remembered run, a proposed command, or a model assertion as execution evidence.
The original controllers in `src/main/java/com/securelabs/vulnerable/` remain a static review corpus.
Old OWASP category IDs in historical samples refer to the 2021 edition. Use the current mapping
in `docs/owasp-reference/top-10-summary.md` in new reports.
