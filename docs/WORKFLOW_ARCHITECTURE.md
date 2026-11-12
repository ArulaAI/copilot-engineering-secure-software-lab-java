# Course workflow architecture

The learner drives a sequential workflow. Seven custom agents (one hidden) define roles and
tools. Five explicitly invoked skills define the stage procedures; each writes its own report. One shared
[evidence contract](../.github/skills/security-review/references/evidence-contract.md) defines
evidence, basis values, scope and limits.

| Stage | Agent selected by learner | Skill | Subagent | Report |
| --- | --- | --- | --- | --- |
| 1A Map the codebase | Codebase Mapper | /map-codebase | Package Explorer, once per package | codebase-map.md |
| 1B Analyze | Security Vulnerability Hunter | /find-vulnerabilities | none | findings.md |
| 2 Threat model and top 5 | STRIDE Threat Modeler | /generate-threat-model | none | threat-model.md |
| 3 Fix SQLI-01 and PATH-01 | Secure Remediation Engineer | /fix-vulnerability | none | change-plan.md |
| 4 Review and red-team (new chat) | Secure Code Reviewer | /review-security-fix | none | review.md |

Every skill writes its own report from the template and appends a section to handoff.md.
Tool lists per agent are in docs/COMPATIBILITY.md.

Tool lists are host-enforced where the harness honors them. File-scope rules ("outputs/ only",
"Maven and git read commands only") are instructions; command approval and diff review are the
learner's checks on them.

## Context design

- **State in files.** Reports carry YAML headers and evidence tables, including each command's
  summary line as printed; outputs/handoff.md holds one short section per stage. Chats are disposable.
- **New chat per stage.** Each reads handoff.md first.
- **A subagent where context is the bottleneck.** Package Explorer reads one package each, so
  mapping a whole codebase returns summaries rather than every file.
- **A new chat for review.** The reviewer starts without the author's reasoning and reruns the
  tests itself. This needs no subagent support and works in any IDE.
- **/compact** when a chat grows, stating what to keep.

Custom-agent handoff buttons are not used because VS Code supports them only in the Local
harness, scheduled for removal in February 2027.

## Gates and limits

The learner agrees scope before edits, approves each command, and ticks each report's gate.
Stage 4 verifies before it attacks. One correction pass and one re-review, then the facilitator.
Shipped exploit tests prove the two assigned fixes; red-team tests live in a separate
`*RedTeamCheck` class so the acceptance suite stays fixed.
`OutputsCheck` validates report form (allowed header values, no placeholders, evidence files
present, consistent finding ID); it does not validate truth.

## Source basis

- [Anthropic: building effective agents](https://www.anthropic.com/research/building-effective-agents)
- [Anthropic: effective context engineering](https://www.anthropic.com/research/effective-context-engineering-for-ai-agents)
- [VS Code: custom agent file reference](https://code.visualstudio.com/docs/agents/reference/custom-agent-file-reference)
- [VS Code: agent skills](https://code.visualstudio.com/docs/agent-customization/agent-skills)
- [VS Code: managing context](https://code.visualstudio.com/docs/agents/concepts/context.md)
- [OWASP GenAI Security Project](https://genai.owasp.org)
