# Secure Software Lab: Understand, Prioritize, Create and Critique

Your team has inherited a synthetic legacy Java service. Before extending it, understand what it
does, investigate its weaknesses and make changes you can defend with evidence. A green build is
only the beginning. Copilot reads and writes; you decide whether its claims and fixes are supported.

## Your route

| Clock | Stage | Outcome |
| --- | --- | --- |
| 0 to 10 | 0 Setup and demonstration | Tools and baseline ready |
| 10 to 22 | 1A Map | Source-grounded architecture and attack surface |
| 22 to 35 | 1B Investigate | Findings, severity and uncertainty |
| 35 to 50 | 2 Threat model | STRIDE, blast radius and five priorities |
| 50 to 70 | 3 Create and critique | Selected tasks independently verified one at a time |
| 70 to 85 | 4 Final review | All changes compared with original findings |
| 85 to 90 | 5 Debrief | Transfer and next actions |

The facilitator normally chooses one or two tasks for live implementation. Continue through more
priorities when time permits or after the session. The workflow has no implementation count limit.
Review and debrief are never cut. The facilitator can shorten discussion of lower-ranked findings
and repeated scoring explanations to protect implementation time, or end the live scope explicitly.
Five completed fixes are not promised within 90 minutes.

![The lab workflow](docs/lab-guide/overview.svg)

## How to drive each stage

1. Start the stage in a new Copilot chat. Select the named agent in the picker.
2. Type `/` and select the existing skill. The skill reads its input files by path; you do not
   paste an operating prompt, attach the whole repository or repeat the previous stage's summary.
3. Inspect the generated report and relevant source/run evidence. Confirm the human gate only
   when supported; record pass, fail or pending honestly.
4. At stage END, switch to **Hand-off Recorder in that same chat** and select `/hand-off`.
   You can also capture an interrupted or pending stage. Recorder saves the current state without
   requiring a gate decision; readiness checks belong to the next stage, not the recorder.
   Check the saved `outputs/handoff.md` section, then start the next stage in a fresh chat.

Role and skill selection are separate. Inspect effective tools and Source Control, including new
files. A scope instruction is not a sandbox; a fresh chat does not guarantee independent judgment.
If a skill or role is missing, call the facilitator. Use only the rehearsed fallback in
[compatibility](docs/COMPATIBILITY.md). No new CLI is required.

## 0. Check your setup (10 minutes)

Complete [compatibility](docs/COMPATIBILITY.md) before the session and read [the scenario](docs/SCENARIO.md).

- Confirm seven visible agents: Codebase Mapper, Security Vulnerability Hunter, STRIDE Threat
  Modeler, Secure Remediation Engineer, Secure Code Reviewer, Hand-off Recorder and OWASP Security
  Expert.
- Confirm seven skills: `/map-codebase`, `/find-vulnerabilities`, `/generate-threat-model`,
  `/fix-vulnerability`, `/review-security-fix`, `/hand-off` and optional `/security-review`.
- Run `mvn clean test` in the terminal. It must build successfully. Some shipped security checks
  are intentionally skipped until explicitly enabled. Inspect the actual summary rather than
  treating green as proof of security. Exact release counts are in [verification](docs/VERIFICATION.md).
- Observe the facilitator demonstrate role selection, skill invocation, a report gate and separate
  handoff. Agents create report files from their templates when needed; you need not copy forms.

## 1A. Map the codebase (12 minutes)

**Question:** what have we inherited, and where does untrusted input enter?

**Input:** actual source/configuration and the scenario.

1. New chat, **Codebase Mapper**, `/map-codebase`.
2. The single mapper inventories packages and reads source plus configuration directly. It retains
   citations while reading, checks actual call edges and writes the map without delegation.
3. Open `outputs/codebase-map.md`. Inspect representative entry points and call edges in source.

**Gate:** actual packages are indexed, externally controlled inputs and resources are named, and
source supports every implemented diagram edge. Mark unknowns. A standalone helper is not an HTTP
endpoint merely because it has a public method. No completed architecture is supplied.

Set the report gate, then **Hand-off Recorder**, `/hand-off` in this chat.

## 1B. Investigate (13 minutes)

**Question:** which weaknesses are supported, and what evidence is still missing?

**Input:** accepted map and mapping handoff, all application classes and relevant configuration. Inspect standalone components too; assess code defects separately from proven external reachability.

1. New chat, **Security Vulnerability Hunter**, `/find-vulnerabilities`.
2. Read `outputs/findings.md`. Inspect the top findings' input-to-sink paths and preconditions.
3. Check severity and coverage, merge duplicate root causes, and reject a plausible false positive
   with a reason. Different titles or finding counts are expected; evidence is the assessment.

**Gate:** findings cite current source and cover all classes/configuration. Code assessment, caller
reachability and actual verification are separate, with uncertainty retained. Preserve this accepted discovery record; later resolution belongs in task/review
artifacts rather than rewriting the original finding away.

Set the report gate, then **Hand-off Recorder**, `/hand-off`.

## 2. Threat model and prioritize (15 minutes)

**Question:** which threats deserve attention first, and why?

**Input:** architecture, accepted Stage 1 findings and discovery handoff. Include supported component risks while preserving caller/exposure uncertainty.

1. New chat, **STRIDE Threat Modeler**, `/generate-threat-model`.
2. Read `outputs/threat-model.md`. Check STRIDE categories, severity, existing controls, impact,
   likelihood and the source/assumptions behind each threat.
3. Inspect scenario-specific account/identifier prerequisites and the score rationale. If a threat
   groups different paths, check which scenario drives its ranking. For the top five, inspect blast radius: who can exploit it, what they reach, the action,
   repeatability and whether it would be noticed. Confirm the priority order.
4. Check that each selected threat has a remediation objective, scope and positive/negative
   acceptance criteria. Independent controls may need bounded subtasks; completing one must not close
   the parent threat while other controls remain open. There is no prescribed list of vulnerability types. Do not invent a fifth
   threat merely to fill a quota if evidence is incomplete; resolve the gap with the facilitator.

**Gate:** five evidence-ranked priorities trace to findings and boundaries. Additional findings
remain visible. Priority is a decision informed by evidence, not proof that all five are Critical.
No mitigation is labelled implemented before code and verification exist.

Set the report gate, then **Hand-off Recorder**, `/hand-off`.

## 3. Secure Implementation (20 minutes; facilitator selects tasks)

Use the tasks below, as in the original lab. Connect your chosen task to the findings and threat
model from Stages 1 and 2. The facilitator decides which tasks to complete live; the rest are follow-up.

For each task:

1. Select **Secure Remediation Engineer** and send the implementation prompt. Approve its plan before edits.
2. In a fresh chat, select **Secure Code Reviewer** and send the review prompt.
3. If the reviewer finds a gap, return to the implementation chat, ask it to address the review findings,
   then review again. Stop when accepted; ask the facilitator for help after three unsuccessful cycles.
4. Verify the current task before moving to the next. The agents save the task state and test evidence.

### Task 1: Secure Authentication

**Implement:**

```text
/fix-vulnerability

Refactor src/main/java/com/securelabs/vulnerable/auth/AuthController.java to:
1. Replace MD5 with BCryptPasswordEncoder, cost factor 12. Update registration and synthetic seed users consistently.
2. Lock an account after 5 failed attempts for 30 minutes. Make time controllable in tests.
3. Secure session expiry, rotation and invalidation using trusted identity.
4. Remove passwords, password hashes and session tokens from unnecessary logs and responses.
5. Use generic authentication errors to avoid revealing whether an account exists.
6. Set HttpOnly, Secure and SameSite attributes on session cookies.

Explain the plan before editing. Add tests for valid login, rejected credentials, lockout and expiry, session behavior and sensitive-data protection. Use existing dependencies.
```

**Verify your fix:** select **Secure Code Reviewer** in a fresh chat and ask:

```text
/review-security-fix

Review my authentication changes in AuthController.java and any supporting files.
Check BCrypt, lockout timing, session controls, cookie attributes and generic errors.
Run the relevant security and regression tests. Confirm legitimate login still works and credentials are not exposed.
Report concrete gaps before accepting the fix.
```

### Task 2: Secure Payment Processing

**Implement:**

```text
/fix-vulnerability

Refactor src/main/java/com/securelabs/vulnerable/api/PaymentHandler.java to:
1. Validate positive amounts up to $1M with at most 2 decimal places, using decimal-safe handling.
2. Allow only USD, EUR and GBP.
3. Verify trusted caller identity and transaction ownership before refunds. Reject excessive or repeated refunds.
4. Remove card numbers and CVVs from logs and unnecessary responses or storage.
5. Verify webhook HMAC signatures over the specified raw payload, with timestamp validation and duplicate-event handling.

Explain the plan before editing. Add local tests for legitimate payment/refund/webhook behavior and rejected inputs, wrong owners, invalid signatures and stale or repeated events. No real payment service is required.
```

**Verify your fix:** select **Secure Code Reviewer** in a fresh chat and ask:

```text
/review-security-fix

Review my payment changes in PaymentHandler.java and any supporting files.
Check amount/currency validation, refund ownership and repeatability, sensitive-data protection and webhook authenticity.
Run the relevant security and regression tests. Verify both permitted operations and rejected attacks without real payment calls.
Report concrete gaps before accepting the fix.
```

### Task 3: Fix SQL Injection

**Implement:**

```text
/fix-vulnerability

Refactor src/main/java/com/securelabs/vulnerable/data/UserRepository.java to:
1. Bind values with PreparedStatement in executed queries and updates.
2. Allow-list field and sort identifiers in searchUsers, findByQuery and related methods.
3. Define and preserve the intended LIKE search behavior.
4. Remove caller-controlled shell execution from exportUsers; agree a safe supported operation or rejection policy.
5. Prevent avatar paths from escaping the intended directory.

Explain the plan before editing. Use the existing H2 fixture to test legitimate queries and hostile values/identifiers. Add local tests for export and avatar boundaries. Preserve existing assertions.
```

**Verify your fix:** select **Secure Code Reviewer** in a fresh chat and ask:

```text
/review-security-fix

Review my repository changes in UserRepository.java and any supporting files.
Confirm query values are parameterized, identifiers are allow-listed, export inputs cannot select commands and avatar paths stay within their root.
Run security and legitimate-behavior tests, including relevant existing opt-in checks, plus regression.
Report concrete gaps before accepting the fix.
```

### Task 4: Fix SSRF and Access Control

**Implement:**

```text
/fix-vulnerability

Refactor src/main/java/com/securelabs/vulnerable/api/ResourceController.java to:
1. Restrict outbound HTTPS destinations using an explicit host/address/port policy, enforced at connection and redirect time.
2. Reject disallowed loopback, private, link-local and IPv6 destinations. Bound response size and time.
3. Require trusted identity and owner/admin authorization on protected resource operations.
4. Validate redirect destinations against an allow-list.
5. Replace wildcard credentialed CORS with explicit allowed origins.

Explain the plan before editing. Add tests for allowed behavior and rejected destinations, redirect escapes, wrong owners and origins. Use controlled local fixtures, not real external or internal services.
```

**Verify your fix:** select **Secure Code Reviewer** in a fresh chat and ask:

```text
/review-security-fix

Review my resource changes in ResourceController.java and any supporting files.
Check connection/redirect destination enforcement, ownership, redirect validation and CORS.
Run tests for authorized behavior and denied attacks, plus regression. Do not treat an annotation or a separate DNS check as proof of enforcement.
Report concrete gaps before accepting the fix.
```

### Task 5: Modern Java API Security (Bonus)

**Implement:**

```text
/fix-vulnerability

Review src/main/java/com/securelabs/vulnerable/api/ModernApiHandler.java and agree a fix scope for:
1. HttpClient destination/redirect restrictions and bounded network operations.
2. Shared mutable state in parallel processing.
3. Password exposure through record toString(), logs and responses.
4. Unrestricted command execution and other supported findings from discovery.

The search endpoint currently builds and returns SQL text without executing it. Do not label that proven database injection or introduce an executor merely to fix it.
Explain the plan before editing and add local security and legitimate-behavior tests for the agreed changes.
```

**Verify your fix:** select **Secure Code Reviewer** in a fresh chat and ask:

```text
/review-security-fix

Review my agreed changes in ModernApiHandler.java and any supporting files.
Check outbound-request policy, correct concurrent results, sensitive-data handling and command restrictions where changed.
Run relevant security and regression tests. Distinguish executed behavior from simulated query construction.
Report concrete gaps before accepting the fix.
```

### Finish implementation

When the facilitator ends live work, select **Hand-off Recorder** in the implementation chat and run
`/hand-off`. Capture completed, unfinished and unresolved work, then continue to Stage 4. Remaining
tasks stay available for follow-up; ending the session does not mark them fixed.

## 4. Final cumulative review (15 minutes, never cut)

**Question:** what changed, what is now supported, and what remains open?

**Input:** original findings, Stage 2 priorities, selected course task cards, cumulative code/test changes, ledger and task critiques.

1. New chat, **Secure Code Reviewer**, `/review-security-fix final`.
2. Inspect `outputs/review.md`. The reviewer compares every original finding with current status,
   checks all actual changes and interactions, and runs completed-task checks plus regression.
3. Inspect ACCEPTED, CHANGES REQUIRED or NEEDS EVIDENCE against the actual evidence. ACCEPTED covers
   verified completed scope only. Deferred, partial and unresolved work must remain visible.
4. A regression returns to the affected task's remaining cycle budget; the reviewer cannot reset
   the budget or edit application code. If time/budget is exhausted, preserve the failing verdict.

**Gate:** completed scope has matching evidence; original findings have current dispositions; no
skipped, deferred or unresolved work is misrepresented as fixed. Inspect residual risks and limits.

Set the final gate, then **Hand-off Recorder**, `/hand-off` in the review chat.

## 5. Debrief (5 minutes, never cut)

1. Which model claim changed after you checked the source or ran a test?
2. Which critique exposed an incomplete fix or broken legitimate behavior?
3. Which prioritized work remains, and what would be needed to finish it?

Take away the map, findings, threat model, task ledger, plan, per-task critiques, final review and
handoff. On your own code, separate evidence from confidence, constrain tool authority, protect
sensitive context and inspect changes before accepting generated output. These are useful AI-security
habits, not a demonstration that prompt injection or all service risks are eliminated.

## If you get stuck

| Symptom | Action |
| --- | --- |
| Agent or skill missing | Stop for facilitator; qualify the existing-procedure fallback |
| Mapping is slow | Inspect repeated reads and excessive output; preserve configuration and source coverage |
| Agent asks for copied logs | Point it to the actual report/evidence path or missing run |
| New task still sees old handoff | Read current ledger/reviews; handoff happens at stage end |
| A task needs a correction | Resume its existing cycle history; never reset across chats |
| Third cycle fails | Mark unresolved and pause for facilitator |
| Test count changes | Inspect discovered classes and task scope; there is no fixed passing total |
| Dependencies or application startup fail | Use the approved support route; do not bypass restrictions |

Optional `mvn -Dtest=OutputsCheck test` checks report form and ledger consistency, not security truth.
Reports not started are skipped. Existing incomplete reports fail rather than proving completion.

**IntelliJ/manual route:** use the rehearsed attachment of the existing agent instructions and phase
SKILL.md, then the one-line request `Follow the attached procedure.` Match roles/tools, save outputs
and run Maven yourself where needed. If execution or separate review context is unavailable, use
facilitator-led review and label the gap. Do not claim native parity or install an extra CLI.

Copyright InRhythm / Arula. Training material using synthetic data.
