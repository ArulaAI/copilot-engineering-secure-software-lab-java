# Workflow architecture

Seven visible agents and seven explicit skills. Mapping uses one agent. Discovery covers the whole
service; Stage 2 models supported findings and ranks five with uncertainty. The guide supplies named
implementation/review prompts. Agents handle any selected task, not preset source-file restrictions.

| Action | Agent | Skill | Output |
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
