# Workflow architecture

Profiles define roles and intended tools. Explicit skills define reusable procedures. Task scope
comes from selected findings and the agreed plan; profiles contain no preset implementation issue IDs,
source-file pairs or test totals. All roles follow the shared contract in .github/skills/security-review/references/evidence-contract.md.

| Action | Agent | Skill | State written |
| --- | --- | --- | --- |
| Map | Codebase Mapper | /map-codebase | codebase-map.md |
| Investigate | Security Vulnerability Hunter | /find-vulnerabilities | findings.md |
| Threat model | STRIDE Threat Modeler | /generate-threat-model | threat-model.md |
| Create/correct one task | Secure Remediation Engineer | /fix-vulnerability | change-plan.md, tasks.md, evidence |
| Critique one task | Secure Code Reviewer | /review-security-fix + named task Critique prompt | task-reviews.md, task status, new critique tests, evidence |
| Cumulative final review | Secure Code Reviewer | /review-security-fix final | review.md, evidence |
| End a stage | Hand-off Recorder, same stage chat | /hand-off | handoff.md only |

Paths in the last column are under outputs/ except new tests. Seven profiles are visible; the Mapper reads source and configuration directly without delegation. Seven explicit skills include the optional /security-review overview.

Stage 2 ranks five priorities from all supported findings. The guide defines the named course assignments and their Create/Critique prompts; these are linked to priorities rather than replaced by a generated task list. Agents support any number of tasks.
The facilitator usually chooses one or two for live implementation, preserving other priorities for
continuation. Three cycles is a maximum per task, not a target. Persist Cycle before each Create.
Critique in a fresh chat independently runs the task tests and regression. Only reviewer acceptance
marks verified; no advancement past unfinished work without an explicit recorded facilitator scope
change. Reopening a task preserves its cycle budget and history.

State lives in reports, the task ledger and append-only critique/cycle history. The separate handoff
reads available state whenever requested, normally at stage end. It records pending/unapproved work
without controlling transitions; stage agents independently check their prerequisites. It does not infer why code changed from diff
alone, fabricate approvals, or ask for duplicated participant summaries. Git status includes new files
that ordinary git diff omits. Evidence records the tested source state.

Final review covers all cumulative changes and every original finding's current disposition. An
accepted partial-session scope does not mean all selected threats were fixed. Deferred and unresolved
work stays explicit. New task tests join subsequent regression. Skipped seeded tests prove no control.

Tool lists require observation in the supported IDE. File-scope text is advisory. Fresh chat is context
separation, not process isolation or guaranteed independent judgment. Native skills, direct mapping,
report writes, command approvals and manual fallbacks all require target rehearsal. No CLI or extra
orchestration runtime is introduced.

- [VS Code custom agents](https://code.visualstudio.com/docs/agent-customization/custom-agents)
- [VS Code skills](https://code.visualstudio.com/docs/agent-customization/agent-skills)
