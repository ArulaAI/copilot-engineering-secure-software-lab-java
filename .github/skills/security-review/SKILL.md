---
name: security-review
description: "Overview of the lab workflow. Use when the learner asks which stage, agent or command comes next."
user-invocable: true
disable-model-invocation: true
---

# Workflow overview

Read `outputs/handoff.md` to see the last completed stage, then tell the learner the next step.
Do not perform any stage yourself.

| Stage | Agent | Command | Writes |
| --- | --- | --- | --- |
| 1A Map the codebase | Codebase Mapper | /map-codebase | outputs/codebase-map.md |
| 1B Analyze | Security Vulnerability Hunter | /find-vulnerabilities | outputs/findings.md |
| 2 Threat model, blast radius, top 5 | STRIDE Threat Modeler | /generate-threat-model | outputs/threat-model.md |
| 3 Fix SQLI-01 and PATH-01 | Secure Remediation Engineer | /fix-vulnerability | outputs/change-plan.md |
| 4 Verify, then red-team | Secure Code Reviewer | /review-security-fix | outputs/review.md |

Each stage starts in a new chat and appends a short section to `outputs/handoff.md`. The
[shared evidence contract](references/evidence-contract.md) defines evidence, basis values and limits.
