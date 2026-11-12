# Security review checklist

## Evidence and scope

- [ ] Every claim cites a file and line or a recorded command result, with a basis.
- [ ] Discovery is independent of facilitator answers, code comments and prior model conclusions.
- [ ] Only the assigned methods changed; variants are recorded, not fixed.

## Injection (SQLI-01 and variants)

- [ ] Values are bound parameters, never concatenated.
- [ ] Identifiers such as sort columns come from an allow-list; anything else is rejected.
- [ ] LIKE wildcards in user input are escaped or the behavior is a recorded decision.

## Paths (PATH-01 and variants)

- [ ] Paths are resolved against the base folder, normalized and checked with Path.startsWith.
- [ ] Rejection happens before any file-system call; absolute and null paths are rejected.
- [ ] Symbolic links and races are stated as limits, not claimed as handled.

## Verification and review

- [ ] The exploit tests failed before the change for the expected reason.
- [ ] Legitimate-behavior tests still pass.
- [ ] Full acceptance passes with none skipped; tests and pom.xml are unchanged.
- [ ] An isolated review verified the fixes before red-team, and gave a qualified verdict.

## Other topics in this codebase

- [ ] Endpoints that read or change another user's data check identity and ownership.
- [ ] Outbound URLs are restricted at connection time, including redirects.
- [ ] Tokens use strong secrets, fixed algorithms and verified signatures.
- [ ] Passwords use a slow, salted hash from an established library.
- [ ] Logs exclude passwords, tokens and card numbers.

Use version-qualified OWASP mappings from the adjacent reference. This checklist does not
establish ASVS compliance or certify the application.
