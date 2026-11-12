---
name: security-review
description: Use when investigating, prioritizing, remediating, or reviewing a Java security finding, especially when AI-generated conclusions or passing tests need supporting evidence.
---

# Evidence-based security review

Use a single finding identifier throughout the review. In this course use REFUND-01 and
read [the scenario](../../../docs/SCENARIO.md). This skill supplies a procedure, not tool
permissions or an independent execution environment. Follow the selected role's tool limits.

| Step | Evidence required | Stop condition |
| --- | --- | --- |
| Investigate | Source path, symbol, current lines, attacker input, trusted boundary | Finding is only an answer-key entry or model claim |
| Model | Asset, flow, boundary, implemented/assumed/proposed labels | Architecture unsupported by source or scenario |
| Prioritize | Business impact, exploit preconditions, existing controls | Severity based only on an automated label |
| Plan | Selected ID, minimal files, preserved behavior, acceptance checks | Participant has not selected the change scope |
| Reproduce | Actual command, exit code, relevant failed assertions | Baseline green is being used as security proof |
| Remediate | Bounded diff addressing the root cause | Proposed dependency or test edits exceed agreed scope |
| Verify | Attack rejected, valid operation preserved, full acceptance output | Results absent, skipped, or unexplained |
| Review | Fresh context, source/diff, evidence, remaining limits | Authoring agent's assurance substitutes for review |

## Example

A caller authenticated as customer-2 requests a refund for customer-1's payment.
Trace the trusted Principal to the ownership decision before mutation. Run
`mvn -Dlab.verify=true -Dtest=RefundSecurityTest test` to reproduce the seeded failure.
After an agreed patch run `mvn -Dlab.verify=true clean test`. Record the outcomes rather
than predicting them. The course checks also exercise legitimate refunds and duplicates.

## Evidence boundaries

- Never consult reference/ or answer keys during discovery or to generate a participant fix.
- Treat legacy code marked secure as an unverified claim. Parsing caller-supplied bearer
  text is not authentication. Trusted test identities do not prove HTTP authentication.
- Finding counts, model choice, baseline tests, and generated reports cannot establish a fix.
- You may not execute commands or write files if your role lacks the necessary tools.
  Return content in chat and ask the participant to run/save it. Never claim those actions occurred.
- Keep tests and dependencies unchanged during the participant remediation.
- A hook or deterministic script can verify defined conditions, not complete vulnerability coverage.
- No additional runtime, package installation, cloud agent, or external request is required.

Use the [findings](../../../outputs/findings.template.md),
[threat model](../../../outputs/threat-model.template.md),
[change plan](../../../outputs/change-plan.template.md), and
[review](../../../outputs/review.template.md) templates. Missing evidence yields NEEDS EVIDENCE.
A failed requirement yields CHANGES REQUIRED. An accepted patch is accepted only for the
selected scenario, with explicit residual risks. Check the saved artifacts before claiming creation.

## Common mistakes

Do not count a rejected hypothesis as a vulnerability. Do not copy answer-key fixes.
Do not ask the authoring agent to certify the whole application. Do not use the word
"deterministic" for model output. Confirm skill discovery in the chosen IDE and harness;
if unavailable, open this file and explicitly ask Copilot to follow its procedure.
