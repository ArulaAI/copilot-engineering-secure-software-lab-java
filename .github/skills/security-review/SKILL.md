---
name: security-review
description: "Overview of the lab workflow. Use when the learner asks which stage, agent or command comes next."
user-invocable: true
disable-model-invocation: true
---

# Lab workflow overview

Read `outputs/handoff.md` to see the last completed stage, then tell the learner the next step.
Do not perform any stage yourself.

| Stage | Agent | Command | Report |
| --- | --- | --- | --- |
| 1A Map the codebase | Codebase Mapper | /map-codebase | codebase-map.md |
| 1B Analyze | Security Vulnerability Hunter | /find-vulnerabilities | findings.md |
| 2 Threat model, blast radius, top 5 | STRIDE Threat Modeler | /generate-threat-model | threat-model.md |
| 3 Fix SQLI-01 and PATH-01 | Secure Remediation Engineer | /fix-vulnerability | change-plan.md |
| 4 Verify, then red-team | Secure Remediation Engineer (calls the reviewer subagent) | /review-security-fix | review.md |

Every stage ends with **Hand-off Recorder** and `/hand-off`. Stages 1A, 1B, 2 and 3 each start
in a new chat; Stage 4 continues in the Stage 3 chat. The
[shared evidence contract](references/evidence-contract.md) defines evidence, basis values and limits.
