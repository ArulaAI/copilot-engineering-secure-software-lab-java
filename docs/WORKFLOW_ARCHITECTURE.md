# Workflow architecture

Seven visible agents and seven explicit skills. Mapping uses one agent. Discovery covers the whole
service; Stage 2 models supported findings and ranks five with uncertainty. The guide supplies named
implementation/review prompts. Agents handle any selected task, not preset source-file restrictions.

| Action | Agent to select | Skill command | Output |
| --- | --- | --- | --- |
| Map | Codebase Mapper | /map-codebase | codebase-map.md |
| Investigate | Security Vulnerability Hunter | /find-vulnerabilities | findings.md |
| Threat model | STRIDE Threat Modeler | /generate-threat-model | threat-model.md |
| Create/correct | Secure Remediation Engineer | /fix-vulnerability | source/tests and concise chat result |
| Task Critique | Secure Code Reviewer | /review-security-fix | short review.md entry |
| Final security review | Secure Code Reviewer | /review-security-fix final | structured security-review.md |
| Capture state | Hand-off Recorder | /hand-off | handoff only |

Report paths are under outputs/. Stage 3 has no mandatory plan, ledger, cycle counter, entry-artifact
snapshot or metadata validator. Existing retired files stay untouched. Create stops after the named
task; Critique stops after its verdict. The participant sends each prompt and chooses the next task.
A fresh reviewer runs actual focused/regression tests. Optional hardening outside the agreed criteria
is follow-up rather than an endless correction loop. Three attempts can guide facilitation without
state machinery. Final acceptance covers completed scope; other priorities stay open.

Generic handoff records available work, including pending decisions, without enforcing sequence.
Actual tests/diffs and concise reviews supply evidence. Tool scopes and fresh chats do not establish
file-level sandboxing or independent judgment. Native Copilot behavior and timing require rehearsal.

Final deliverable: Stage 4 generates one structured outputs/security-review.md from its template,
with verdict, scope, fixed/open findings, ASVS/control-area gaps and actual build/test results. Stage 3
review.md remains a short per-task input. No duplicate final append, new whole-service scan or metadata
loop is required. The report is scoped evidence, not production/ASVS certification.

## Skills and bundled formats

Each skill identifies its corresponding agent and links its profile. Select that agent before invoking
the skill. Skills contain procedures and templates; agent profiles define the role and available tools.
There are no prompt wrappers. Inline skills run in the current agent context and do not switch roles.
`/security-review` uses OWASP Security Expert for workflow questions only.

| Skill | Bundled templates | Generated output |
| --- | --- | --- |
| map-codebase | templates/codebase-map.template.md | outputs/codebase-map.md |
| find-vulnerabilities | templates/findings.template.md | outputs/findings.md |
| generate-threat-model | templates/threat-model.template.md | outputs/threat-model.md |
| review-security-fix | templates/review.template.md, templates/security-review.template.md | outputs/review.md, outputs/security-review.md |
| hand-off | templates/handoff.template.md | outputs/handoff.md or chosen path |

fix-vulnerability changes source/tests and reports in chat; security-review explains the workflow.
Neither needs a template. Never write generated reports into a skill's templates folder.

## Removed approval checkpoints

Map approval, discovery approval and its handoff prerequisite, threat-priority sign-off, per-stage
handoff requirements and the form validator's pass/fail gate fields have been removed. Existing
reports with legacy gate fields can be read as historical data; those fields never block execution.
Missing evidence is described honestly and limits completeness claims. Review verdicts and actual
security/regression checks remain: removing approval paperwork cannot turn an unverified fix into PASS.

[Inline skills](https://code.visualstudio.com/docs/agent-customization/agent-skills) supply reusable procedures and resources.
