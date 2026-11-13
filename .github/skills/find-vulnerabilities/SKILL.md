---
name: find-vulnerabilities
description: "Investigate mapped inputs and rank evidence-backed findings."
argument-hint: "Optional flow to investigate"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Select Security Vulnerability Hunter or stop. Read the map, then trace untrusted inputs to sinks across real callers. Record severity, preconditions, missing control, impact, source locations and uncertainty. Merge duplicate root causes, reject a plausible false positive with evidence and disclose coverage gaps. Do not report simulated query construction as proven database execution. Write outputs/findings.md from its template and preserve it as the original discovery record once accepted. Record stage-entry source state. Ask for the human gate, then direct /hand-off in the same chat with Hand-off Recorder. Do not update handoff yourself.
