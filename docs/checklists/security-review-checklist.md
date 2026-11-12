# Security review checklist

## Evidence and scope

- [ ] Selected finding, threat, plan, diff, and tests share an identifier.
- [ ] Claims distinguish observed behavior, inference, assumptions, and coverage limits.
- [ ] Discovery is independent of facilitator answers and prior model conclusions.

## Identity and authorization

- [ ] Trusted identity comes from an explicit upstream boundary.
- [ ] Caller-controlled IDs, bearer text, or admin-looking names confer no privilege.
- [ ] Object ownership is checked before state mutation.
- [ ] Valid owner behavior remains available after a rejected attack.

## Business behavior and state

- [ ] Amount, precision, currency, and full-refund rules are enforced.
- [ ] Duplicate/concurrent calls cannot produce multiple refunds.
- [ ] Fixture atomicity is distinguished from a production database transaction.

## Verification and review

- [ ] The relevant acceptance check failed before the change for the expected reason.
- [ ] Actual implementation methods are tested, with legitimate and unauthorized cases.
- [ ] Full enabled acceptance passes, with commands, exit status, and skipped counts recorded.
- [ ] Tests, dependencies, and unrelated source are unchanged by the participant fix.
- [ ] A fresh review inspects source and evidence and states a qualified verdict.

## Optional review topics

- [ ] SQL values are parameterized and dynamic identifiers allowlisted.
- [ ] Cryptographic configuration errors fail closed and signatures bind exact payloads.
- [ ] Time windows and event deduplication are assessed separately.
- [ ] SSRF policy applies at connection time, retries, and redirects with resource limits.
- [ ] Session expiry is enforced server-side and path checks consider symlink assumptions.

Use version-qualified OWASP mappings from the adjacent reference. This checklist does not
establish full ASVS compliance or certify the historical application.

## AI-assisted workflow threat view

- [ ] Java application and engineering assistant are separate modeled subjects.
- [ ] Relevant ASI/LLM identifiers include the verified edition and mapping rationale.
- [ ] Input provenance, trusted task, tool authority, action sink and consequence are explicit.
- [ ] Intended tool restrictions are distinguished from observed host enforcement.
- [ ] Control type and actual observation or untested status are recorded.
- [ ] Absent features are not invented and passing Java tests are not AI-security evidence.

Use the [AI reference](../owasp-reference/ai-security.md) for definitions and official sources.
