# Course workflow architecture

The learner drives a sequential workflow. Eight custom agents (one hidden) define roles and
tools. Explicitly invoked skills define the stage procedures and the hand-off. One shared
[evidence contract](../.github/skills/security-review/references/evidence-contract.md) defines
evidence, basis values, scope and limits.

| Stage | Agent selected by learner | Skill | Subagent | Report |
| --- | --- | --- | --- | --- |
| 1A Map the codebase | Codebase Mapper | /map-codebase | Package Explorer, once per package | codebase-map.md |
| 1B Analyze | Security Vulnerability Hunter | /find-vulnerabilities | none | findings.md |
| 2 Threat model and top 5 | STRIDE Threat Modeler | /generate-threat-model | none | threat-model.md |
| 3 Fix SQLI-01 and PATH-01 | Secure Remediation Engineer | /fix-vulnerability | none | change-plan.md |
| 4 Verify, then red-team | Secure Remediation Engineer | /review-security-fix | Secure Code Reviewer (isolated verify and attack list) | review.md |
| End of every stage | Hand-off Recorder | /hand-off | none | handoff.md |

| Agent | Tools | Why |
| --- | --- | --- |
| Codebase Mapper | read, search, agent | Fans out one subagent per package, stitches the map |
| Package Explorer (hidden) | read, search | Summarizes one package in its own context |
| Security Vulnerability Hunter | read, search | Analysis needs no write access |
| STRIDE Threat Modeler | read, search | Scoring and prioritization from files |
| Secure Remediation Engineer | read, search, edit, execute, agent | Fixes, runs tests, calls the reviewer subagent |
| Secure Code Reviewer | read, search, execute | Reruns tests itself; cannot edit the fix it judges |
| Hand-off Recorder | read, search, edit | Writes reports and hand-offs under outputs/ only |
| OWASP Security Expert | read, search | Optional explanations |

Tool lists are host-enforced where the harness honors them. File-scope rules ("outputs/ only",
"Maven and git read commands only") are instructions; command approval and diff review are the
learner's checks on them.

## Context design

- **State in files.** Reports carry YAML headers and evidence tables; run logs live in
  outputs/evidence/; outputs/handoff.md holds one short section per stage. Chats are disposable.
- **New chat per stage** for Stages 1 to 3. Each reads handoff.md first.
- **Subagents for isolation.** Package Explorer reads one package each, so mapping a whole
  codebase returns summaries rather than every file. The reviewer gets only the fix IDs and file
  list, so it judges evidence, not the author's reasoning.
- **Agent switch for the hand-off.** The Recorder runs in the same chat so it can see the stage.
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
