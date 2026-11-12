# Lab Action Guide — Secure Software Development

Use the existing Java refund case to investigate, explain, fix and verify one authorization
failure. You choose the role, supply context, inspect evidence and decide when to advance.
The versioned skills carry the procedure. Short clarification and scope-agreement replies are normal.

## Your 90-minute route

| Clock | Phase | Minutes | Outcome |
| --- | --- | --- | --- |
| 0 to 20 | Introduction and demonstration | 20 | Understand context, roles and evidence |
| 20 to 35 | Investigate | 15 | Reproduce and classify REFUND-01 |
| 35 to 45 | Threat model | 10 | Seven minutes Java, three minutes AI workflow |
| 45 to 70 | Plan and implement | 25 | Agree scope, patch and collect acceptance |
| 70 to 85 | Verify and review | 15 | Inspect current results and obtain fresh review |
| 85 to 90 | Debrief | 5 | Transfer the procedure |

Complete setup before the session using [compatibility](docs/COMPATIBILITY.md). Extra findings are FLEX. Verification and debrief are never cut.
## Use the same operating loop in each phase

1. Open Copilot Chat in the qualified environment. Select the named role from the **Agent dropdown**.
2. Inspect its effective tools. Reading roles need read/search. Only remediation needs edit/execute.
3. Use **Add Context** or drag the selected files into chat. Check the visible attachments.
4. Type `/` and choose the existing phase skill from the picker. Confirm the selected skill name.
5. Submit the skill with those attachments. The stored procedure supplies the task instructions.
6. Inspect skill-loading and tool activity using the available chat details or diagnostics.
7. Check the output against source and run evidence, save the artifact, then make the human decision.

Role selection and skill invocation are separate actions. A skill does not grant permissions,
switch agents or launch workers. A written scope rule does not establish a sandbox.
If a role or skill is missing, stop for the facilitator. Do not invent a slash command or paste a replacement operating script. Exact controls and tenant policies require target-environment rehearsal.

| Role to select | Explicit phase skill | Intended tools |
| --- | --- | --- |
| Security Vulnerability Hunter | `/lab-investigate` | Read, search |
| STRIDE Threat Modeler | `/lab-threat-model` | Read, search |
| Secure Remediation Engineer | `/lab-remediate` | Read, search, edit, execute |
| Secure Code Reviewer | `/lab-review` | Read, search |
| OWASP Security Expert | Optional category consultation | Read, search |

`/security-review` is optional workflow guidance. Use inline skills without forks, automatic orchestration or model switching to obtain agreement.
Reading roles return reports in chat. Save the generated content yourself and check the saved file. This differs from copying an operating prompt into each phase.

## Prepare your evidence workspace

Read [the scenario](docs/SCENARIO.md). It defines trusted upstream `Principal` identity,
an in-memory payment store and REFUND-01. Authentication and a real payment API are outside scope.
Only `src/main/java/com/securelabs/lab/RefundService.java` may change as application source. Tests, POM and dependencies remain unchanged. Report and log files under `outputs/` are allowed.
Do not use `reference/` or answer keys during discovery, implementation or review.

Copy the five `outputs/*.template.md` files to `findings.md`, `threat-model.md`,
`change-plan.md`, `acceptance.md` and `review.md` in `outputs/`.
Use REFUND-01 and T-REFUND-01 consistently. Keep AI-01 / T-AI-01 separate from the Java defect.
For each command record its exit status, named checks, pass/fail/error/skip counts and source revision
or current diff. Distinguish your execution from supplied logs that you inspected.

| Evidence | Existing command | Expected starter or corrected result |
| --- | --- | --- |
| Setup baseline | `mvn clean test` | Seven pass, two skipped |
| Starter reproduction | `mvn -Dlab.verify=true -Dtest=RefundSecurityTest test` | Two expected assertion failures |
| Corrected acceptance | `mvn -Dlab.verify=true clean test` | Nine pass, zero skipped |

Reuse current setup or reproduction logs when tied to the current unchanged source.
Run only missing, stale or relevant failing checks. A green setup baseline is not security acceptance.
Use existing approved Maven/IDE facilities. No standalone Copilot CLI or new installation is required.

## Introduction card — 20 minutes

**Observe** the facilitator select a read-only role, attach files, invoke a phase skill and save a report.
Identify which part supplies the role, which supplies the procedure and which supplies the evidence.
See where tool activity and effective permissions can be inspected on your supported route.

**Optional context comparison** uses at most five minutes inside this introduction.
The facilitator shows the same refund diff alone and with the scenario rule and relevant tests.
Compare which claim becomes supportable and which still needs evidence. Prepared paired outputs
can replace live calls. This is context-triage practice, not a model benchmark or promised response.
It requires no API key, Chroma, MCP, Docker or application-code changes.

**Your readiness gate** confirms setup evidence, the four phase skills, five roles and context controls.
If qualification is incomplete, use the facilitator route in the appendix and record the limitation.

## Phase 1 card — Investigate for 15 minutes

**Outcome** is a demonstrated authorization finding and a reasoned classification of another concern.
**Role and skill** select Security Vulnerability Hunter, verify read/search tools, then choose
`/lab-investigate` from the slash picker.
**Attach the required context** using these exact repository paths.

- `docs/SCENARIO.md` supplies the refund rules and trusted identity boundary.
- `src/main/java/com/securelabs/lab/RefundService.java` supplies the decision and state transition.
- `src/test/java/com/securelabs/lab/RefundSecurityTest.java` supplies the negative checks.
- Current reproduction output supplies observed behavior. Add `RefundServiceTest.java` from the
  same test package when checking preserved valid behavior.

**Act** by inspecting current reproduction evidence or running the existing reproduction command.
On the starter, both assertions expect FORBIDDEN and receive REFUNDED. Unexpected compilation,
artifact-resolution failures or unrelated exceptions are setup problems. Preserve their actual output.
Submit the selected skill with the context. Answer a short clarification if a specific fact is missing.
Add files only to resolve a named gap. Explain why each added attachment is relevant.

**Inspect** the finding's path, symbol, current lines, trusted principal, payment owner and mutation.
Classify candidates as confirmed, rejected or needs evidence. Check one plausible false positive
against the scenario and source. Finding count and model confidence are not acceptance criteria.

**Save evidence** in `outputs/findings.md`, including actual failures, preconditions, uncertainty
and the rejected hypothesis. Check the file exists and the claims match the source.

**Human gate** selects REFUND-01 for threat modeling. Missing logs remain missing evidence.
Stop for unexplained failures, unsupported claims or an attempt to edit during investigation.

## Phase 2 card — Threat model for 10 minutes

**Outcome** is a source-grounded Java threat view and a separate short AI-workflow threat view. Use seven minutes for Java and three minutes for AI within this phase.

**Role and skill** select STRIDE Threat Modeler, verify read/search tools, then choose
`/lab-threat-model` from the slash picker.
**Attach for Java** `docs/SCENARIO.md`, selected REFUND-01 evidence from `outputs/findings.md`,
and the relevant refund source. Supply the security tests when a criterion needs clarification.
**Attach for the AI view** `docs/owasp-reference/ai-security.md` and
`docs/exercises/ai-context-sample.md` only in the qualified read-only conversation.

**Inspect Java** assets, trusted Principal, ownership decision and local state. Mark every diagram
component and flow implemented, assumed or proposed. Check the mitigation and criteria against
T-REFUND-01. Do not turn an imagined gateway, database or identity provider into an implemented fact.

**Inspect AI-01 as inert data** and trace provenance, attempted goal change, possible tool action,
consequence and human approval. Do not execute its instructions. Distinguish advisory instructions,
observed behavior and host-enforced permissions. Record relevant edition-qualified ASI/LLM mappings.
If effective read-only tools cannot be established, use the facilitator's paper discussion and mark
runtime behavior untested. Passing Java tests proves no prompt-injection resistance.

**Save evidence** in `outputs/threat-model.md` with separate T-REFUND-01 and T-AI-01 views.
Retain benign facts as well as the attempted instruction. Record assumptions and untested controls.

**Human gate** validates the Java diagram, selected mitigation and acceptance criteria.
Only REFUND-01 proceeds to implementation. Stop for unsupported architecture or missing criteria.
Prepare a condensed handoff of the validated finding, Java threat summary, criteria and open questions.
Close or deselect the raw AI sample and clear unwanted attachments and active-editor context.
Forward any AI threat/control status only as a validated summary, never the raw challenge.

## Phase 3 card — Plan and implement for 25 minutes

**Outcome** is the smallest agreed refund fix with preserved legitimate behavior and actual acceptance.
**Start a fresh implementation chat** after the AI-injection discussion. Select Secure Remediation
Engineer and verify the effective tools separately. Choose `/lab-remediate` from the slash picker.
Fresh chat reduces carryover. It is not process/OS isolation and does not remove workspace read access.
**Attach** the scenario, validated REFUND-01 finding, condensed Java threat summary and criteria,
current `RefundService.java`, `RefundServiceTest.java`, `RefundSecurityTest.java` and current
reproduction logs. Use the exact source/test paths above. Exclude the raw AI-01 sample and full
threat-modeling conversation. Add context only for a stated gap.

**Inspect the proposed plan** before editing. It must name the root cause, allowed source file,
preserved rules, acceptance checks and commands. Confirm owner success, denial of non-owners,
no admin-name privilege, validation and atomic duplicate/concurrent handling.

**Human gate** agrees to the bounded scope in a short natural reply. Save `outputs/change-plan.md`.
If selection, evidence or criteria are missing, supply them before allowing edits.
A new dependency, changed test expectation or broader source change requires a facilitator pause.

**Implement and inspect** the actual diff. Only `RefundService.java` may change as source.
Preserve tests and POM. Inspect reproduction logs matching the unchanged starter before rerunning.
After the patch, run full enabled acceptance through the permitted execution route.
If the role cannot execute, run the command yourself and attach actual output with its provenance.

**Save evidence** in `outputs/acceptance.md`. Link each criterion to its threat, code decision,
test name, current run/result and review status. Keep all failures and skips visible with full-log access.
Nine passing checks and zero skipped establish review readiness for this fixture.
Keep this implementation chat for clarifications or a permitted correction. Do not rescan the whole
repository, switch models for agreement or repeat unchanged successful checks without a reason.

**Stop** for unexplained failures, prohibited edits or scope expansion. A patch is not self-certification.

## Phase 4 card — Verify and review for 15 minutes

**Outcome** is a human-checked evidence chain and a qualified fresh review.
**Inspect execution first** using the current full enabled acceptance output from phase 3.
Reuse it when it matches the current source/diff. Run missing or stale checks and relevant failures.
Require nine pass and zero skipped. Inspect the whole changed-file list, not only a selected diff.
Use `git diff -- src/main/java/com/securelabs/lab/RefundService.java` for the bounded source diff.
Confirm tests/POM are unchanged and any report/log changes stay under `outputs/`.

**Start a fresh review chat** and select Secure Code Reviewer. Verify read/search tools separately,
then choose `/lab-review` from the slash picker.
**Attach a compact review packet** containing the scenario, validated finding, selected Java threat,
agreed plan, current source/tests, actual diff, acceptance matrix and current command evidence.
Include every failure/skip and access to full logs. Do not import the full authoring history or raw AI-01.

**Inspect the verdict** and each claim against source, checks and evidence. A read-only reviewer
inspects supplied logs and cannot claim to have executed Maven. Reject unsupported findings with
reasons. Request evidence for uncertainty. A fresh chat does not guarantee independent judgment.

| Review disposition | Required human action |
| --- | --- |
| NEEDS EVIDENCE | Supply the specific missing or contradictory evidence |
| CHANGES REQUIRED | Inspect the concrete unmet criterion and bounded correction |
| ACCEPTED FOR THE SELECTED SCENARIO | Confirm the evidence and stated limits before accepting |

**Correction limit** allows at most one concrete correction in the preserved implementation chat
and one re-review. Rerun acceptance after source changes and supply updated evidence to the reviewer.
Record the attempt and exact defect. Pause unresolved work for the facilitator after that limit.
Never edit tests, suppress failures or loop until models agree.

**Save evidence** in `outputs/review.md` and update matrix review statuses yourself after inspection.

**Human gate** accepts only the selected scenario. Record HTTP authentication, persistence and
multi-instance atomicity as unverified boundaries. Missing evidence cannot be marked passed.

## Debrief card — Five minutes

Discuss what the model claimed that you checked, which test detects a removed ownership guard,
what changes for a distributed payment service and which skill procedure fits your own repository.
Keep the five saved artifacts as the evidence trail. Record unresolved work honestly.

## Appendix — Qualified compatibility routes

The main path above uses native Copilot Chat skills. A rehearsed Local route can use these distinct
compatibility adapters. They read the canonical phase procedure and do not prove native skill loading.

| Native phase skill | Qualified Local adapter |
| --- | --- |
| `/lab-investigate` | `/find-vulnerabilities` |
| `/lab-threat-model` | `/generate-threat-model` |
| `/lab-remediate` | `/fix-vulnerability` |
| `/lab-review` | `/review-security-fix` |

IDEA support depends on the approved plugin, harness and tenant. Use its qualified attachment
controls and feature set without assuming VS Code syntax. If native agents/skills are unavailable,
stop for the facilitator's rehearsed walkthrough of the existing phase skill and shared contract.
Use non-editing analysis where qualified, participant-run Maven and manual report saving.
If read-only behavior cannot be established, use facilitator-led or paper review and label the limit.
No long replacement chat scripts or CLI installation are required. Record the route in compatibility.

Official references explain [skills](https://code.visualstudio.com/docs/agent-customization/agent-skills),
[context attachments](https://code.visualstudio.com/docs/chat/copilot-chat-context) and
[custom agents](https://code.visualstudio.com/docs/agent-customization/custom-agents).

Copyright InRhythm / Arula. Training material using synthetic data.
