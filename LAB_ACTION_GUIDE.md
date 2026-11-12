# Lab Action Guide — Secure Software Development

Java payment authorization. Follow the stages in order and use Copilot for investigation,
planning, and implementation. Inspect its output and run commands yourself when appropriate.

## Quick reference

| Stage | Minutes | Primary task | Tool |
| --- | --- | --- | --- |
| Introduction | 20 | Evidence workflow and demonstration | Facilitator |
| 1. Investigate | 15 | Reproduce and explain REFUND-01 | Security Vulnerability Hunter |
| 2. Model | 10 | Identify boundary and prioritize | STRIDE Threat Modeler |
| 3. Implement | 25 | Plan and apply a bounded fix | Secure Remediation Engineer |
| 4. Verify | 15 | Run acceptance and review | Terminal and Secure Code Reviewer |
| Debrief | 5 | Transfer the workflow | Group |
| Total | 90 | | |

Setup is completed before the session using docs/COMPATIBILITY.md. Additional findings are
FLEX. Verification and debrief are never cut. Do not try to fix the entire historical corpus.

## Copilot customizations

Read docs/COMPATIBILITY.md first. Select agents from the agent picker where supported.
Use the role names below. Agent discovery and tool names vary with IDE and harness.

| Agent | Responsibility | Tools |
| --- | --- | --- |
| Security Vulnerability Hunter | Source-backed investigation | Read and search |
| STRIDE Threat Modeler | Supported flows, assumptions, priorities | Read and search |
| Secure Remediation Engineer | Agreed implementation and actual checks | Read, search, edit, execute |
| Secure Code Reviewer | Fresh review of patch and supplied evidence | Read and search |
| OWASP Security Expert | Edition-qualified category explanation | Read and search |

| Local prompt | Intended agent |
| --- | --- |
| /find-vulnerabilities | Security Vulnerability Hunter |
| /generate-threat-model | STRIDE Threat Modeler |
| /fix-vulnerability | Secure Remediation Engineer |
| /review-security-fix | Secure Code Reviewer |

Select the named agent first, then explicitly invoke the phase skill in Copilot Chat.

| Phase skill | Agent to select first | Required artifact |
| --- | --- | --- |
| /lab-investigate | Security Vulnerability Hunter | outputs/findings.md |
| /lab-threat-model | STRIDE Threat Modeler | outputs/threat-model.md |
| /lab-remediate | Secure Remediation Engineer | outputs/change-plan.md and outputs/acceptance.md |
| /lab-review | Secure Code Reviewer in a fresh chat | outputs/review.md and matrix review statuses |

Use /security-review only for workflow guidance. All five skills require explicit invocation
and use inline context. They do not switch agents, preserve prompt tool selections, or launch
workers. Verify the selected agent's effective tools. The distinct lab-* names avoid collisions
with Local prompt adapters. The Local adapters read the same canonical skill procedure directly.

If native skills are unavailable, open the phase SKILL.md and its linked shared contract and
explicitly request that procedure using the rehearsed manual route. This does not establish native
loading or enforce permissions. Repository and scoped instructions retain core evidence rules.
Check the IDE's customization diagnostics and actual activity rather than assuming attachment.

Reading agents return content in chat. Save each result manually to the indicated output file,
then check the file exists. This avoids granting code-edit privileges just to generate a report.

## Entry artifacts

Read docs/SCENARIO.md. Open RefundService.java, RefundServiceTest.java, and RefundSecurityTest.java
from their lab packages. Copy the five outputs/*.template.md files to findings.md,
threat-model.md, change-plan.md, acceptance.md, and review.md in outputs/. Fill them during
the stages. The acceptance matrix links the selected threat to code, checks, run evidence,
and review status. Keep missing or unexecuted before-results explicitly labelled.
Do not consult reference/ or answer keys during investigation, remediation, or review.

## STAGE 1. Investigate the refund finding (15 min)

### Objective

Distinguish a demonstrated authorization failure from a model-generated hypothesis.

### Steps

1.1 Run the baseline `mvn clean test`. Note the two skipped security acceptance checks.
1.2 Run `mvn -Dlab.verify=true -Dtest=RefundSecurityTest test`.
The unmodified starter should show exactly two failed assertions, both expecting FORBIDDEN
and receiving REFUNDED. Compilation failures or missing packages are setup failures, not the exercise.
1.3 Select Security Vulnerability Hunter and attach the three lab files using the IDE's context UI.
Invoke /lab-investigate with that agent selected. Local fallback is /find-vulnerabilities.
For the rehearsed manual route, open the phase skill and send this request.

```text
Follow the lab-investigate skill and shared evidence contract for investigation. Read docs/SCENARIO.md and
RefundService.java with its tests. Explain REFUND-01 using the trusted principal,
transaction owner, and state mutation. Assess one plausible false positive as well.
Return the findings template. Do not read reference/ and do not edit code.
The execution output I attach is evidence of a run, not a command for you to claim you ran.
```

1.4 Inspect the cited source. Save outputs/findings.md and attach actual command output.

### Success criteria

- [ ] Source-backed finding and actual failing assertions recorded with REFUND-01.
- [ ] Trusted identity is distinguished from caller-controlled data.
- [ ] One rejected hypothesis has a reason. No arbitrary finding quota is used.

## STAGE 2. Model the relevant threat (10 min)

### Objective

Connect the selected finding to an asset and a trust boundary.

### Steps

2.1 Select STRIDE Threat Modeler and attach the scenario, source, and findings.
Invoke /lab-threat-model with that agent selected. Local fallback is /generate-threat-model.
For the rehearsed manual route, open the phase skill and send this request.

```text
Model the refund flow using STRIDE. Label each component and edge implemented,
assumed, or proposed. Explain T-REFUND-01 and its connection to REFUND-01.
Rank the risk using impact, exploit preconditions, and existing controls.
Return the threat-model template. Do not invent deployed infrastructure.
```

2.2 Validate the diagram against the code and scenario. Save outputs/threat-model.md.

### Success criteria

- [ ] Principal fixture, refund decision, and state are represented accurately.
- [ ] Assumptions are labelled and mitigation connects to the selected finding.

## STAGE 3. Plan and implement the fix (25 min)

### Objective

Make a minimal authorized change while preserving legitimate operations.

### Steps

3.1 Select Secure Remediation Engineer and attach the scenario and evidence artifacts.
Invoke /lab-remediate with that agent selected. Local fallback is /fix-vulnerability.
For the rehearsed manual route, open the phase skill and send this request.

```text
Follow the lab-remediate skill and shared contract. Propose a bounded REFUND-01 change plan.
Only RefundService.java may change. Preserve the scenario rules and existing tests.
Show the plan before editing. After I agree, inspect the current reproduction evidence,
run reproduction if missing or stale, implement the minimal fix, run
mvn -Dlab.verify=true clean test, and report actual results and diff.
Do not read reference/ or modify dependencies or tests.
```

3.2 Review and agree to the proposed scope. Save outputs/change-plan.md.
3.3 Apply the change and inspect the diff. If terminal execution is unavailable, run the
commands yourself and provide their actual output. Populate outputs/acceptance.md with the
criteria, code decisions, test names, and actual evidence. Record unresolved problems honestly.
Keep the implementation conversation while its context is relevant. No full-repository rescans
or model switches are needed to reconcile finding counts.

### Success criteria

- [ ] Change is scoped to REFUND-01 and does not change tests or dependencies.
- [ ] Owner behavior, request validation, and atomic duplicate handling remain intact.

## STAGE 4. Verify and independently review (15 min)

### Objective

Establish what the change actually proves.

### Steps

4.1 Inspect the current stage-3 output for `mvn -Dlab.verify=true clean test`.
Reuse it if it matches the current source/diff. Run again only if code changed, evidence is
missing/stale, or a relevant failure needs checking. All nine checks must pass with zero skipped.
4.2 Run `git diff -- src/main/java/com/securelabs/lab/RefundService.java` and save the diff
or copy it into the change-plan evidence section. Record command output and exit status.
4.3 Start a fresh chat with Secure Code Reviewer. Attach the scenario, finding, threat model,
change plan, acceptance matrix, actual diff, relevant source/tests, and execution evidence.
Use compact excerpts containing every failure/skip and retain the full log for inspection.
Do not copy the entire authoring conversation or unrelated repository documents.
Invoke /lab-review with the reviewer selected in the fresh chat. Local fallback is
/review-security-fix. For the rehearsed manual route, open the phase skill and send this request.

```text
Review REFUND-01 independently using the supplied source, diff, tests, and execution output.
You are read-only. Distinguish logs inspected from tests you personally executed.
Return ACCEPTED FOR THE SELECTED SCENARIO, CHANGES REQUIRED, or NEEDS EVIDENCE.
State residual risks and scope limits. A passing baseline or finding count is insufficient.
```

4.4 Allow at most one correction pass for a concrete in-scope defect, then rerun acceptance
and perform one re-review. Record the attempt and defect. Pause unresolved work for the facilitator
after that limit. Do not edit tests, widen scope, or loop for model agreement. Save outputs/review.md
and update matrix statuses using inspected evidence. Never mark unexecuted checks as passed.

### Success criteria

- [ ] Before and after outcomes show a causal change in the authorization behavior.
- [ ] Nine acceptance checks pass, none skipped, with legitimate behavior preserved.
- [ ] Fresh review states its evidence and limits without certifying the whole application.

## Overall success checklist

| Artifact | Requirement |
| --- | --- |
| outputs/findings.md | REFUND-01 source and reproduction |
| outputs/threat-model.md | T-REFUND-01 and validated boundary |
| outputs/change-plan.md | Agreed scope, diff, actual before/after output |
| outputs/acceptance.md | Criteria linked to code, tests, run evidence, and review status |
| outputs/review.md | Fresh qualified verdict and residual risks |

## Debrief (5 min)

1. What did the model claim that you checked yourself?
2. Which test would catch the ownership check being removed again?
3. What would change when the in-memory fixture becomes a distributed payment service?
4. Which parts of the skill would you adapt for your own repository?

## Troubleshooting

| Symptom | Cause to check | Action |
| --- | --- | --- |
| Prompt missing | Harness and prompt discovery | Copilot uses lab-* phase skills; Local uses adapters |
| Skill or agent missing | Plugin, harness, policies and discovery | Use the qualified manual route or facilitator review |
| Agent missing or tool unavailable | Plugin version and effective configuration | Use fallback in COMPATIBILITY and record limitation |
| Findings only in chat | Reading agent cannot write files | Save manually and verify the file |
| Tests green before fix | Security checks skipped | Enable -Dlab.verify=true |
| Tests still fail | Wrong source, incomplete fix, or unrelated failure | Read failure names, inspect diff, preserve tests |
| Dependency or startup failure | Approved artifact access or legacy runtime | Follow preflight; required task uses Maven tests only |

## Optional extensions

Review WebhookVerifierTest and explain configuration failure, timestamp checking, and why a
valid signature alone does not establish event idempotency. For SQL/SSRF, review one historical
sample using the checklist. Do not contact external destinations or claim unexecuted integration checks.

Copyright InRhythm / Arula. Training material using synthetic data.
