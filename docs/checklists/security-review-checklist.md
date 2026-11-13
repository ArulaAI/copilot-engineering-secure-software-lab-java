# Review checklist

## Every selected task

- [ ] Trace original finding, selected threat and agreed acceptance criteria to current source.
- [ ] Confirm the actual input-to-sink path and distinguish runtime assumptions.
- [ ] Inspect all changes, including new tests, against agreed scope.
- [ ] Verify root cause removal, allowed behavior and error handling; do not accept reject-everything fixes.
- [ ] Independently execute relevant security, legitimate-behavior and previous-task regression checks.
- [ ] Add bounded adversarial checks with local synthetic fixtures; inspect failures before classifying an exploit.
- [ ] Preserve existing assertions and disclose skipped or unrun checks.
- [ ] Record matching source state, full run evidence and verdict. No self-certification.
- [ ] Preserve the task's Create cycle and history, with no fourth cycle or automatic advancement after failure.

## Domain questions, where relevant

- Query values are bound; identifiers are explicitly allowed. Literal versus wildcard search semantics are agreed.
- Paths remain inside the permitted base for the intended filesystem model. Symlinks/races are handled or stated as limits.
- Identity comes from a verified local boundary, not an unchecked request field; test denied and permitted users.
- Outbound requests enforce destination policy at connection/redirect time. No real external probing in the lab.
- Commands and deserialization are limited by an explicit use case; test rejected and allowed operations locally.
- Passwords, tokens, secrets and sensitive fields are not exposed through logs, responses or error messages.

## Final cumulative review

- [ ] Every original finding has a current disposition with code/run evidence or a stated gap.
- [ ] Completed tasks still pass after later changes; inspect interactions.
- [ ] Deferred, unstarted, partial and unresolved work is not marked fixed.
- [ ] Acceptance covers completed scope only, with residual risks and unverified boundaries.

Use edition-qualified references. This checklist does not certify production security or compliance.
