---
name: generate-threat-model
description: "Model all supported findings and select evidence-ranked priorities."
argument-hint: "Optional business context"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Select STRIDE Threat Modeler or stop. Read the architecture and ALL original findings. Apply STRIDE across relevant service flows, assets and trust boundaries. Group related findings into threats, retaining finding IDs. Record severity, impact, likelihood, existing controls, blast radius, assumptions and validation gaps. Rank the shortlist requested in the scenario; do not force any named vulnerability into it. Highest priority does not imply every threat is Critical. For each selected threat define a bounded remediation objective and positive/negative acceptance criteria; do not claim it fixed. Write outputs/threat-model.md from its template. The participant confirms priorities. Then direct /hand-off with Hand-off Recorder in this same chat. Do not update handoff yourself.
