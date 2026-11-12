---
name: security-review
description: Use when the learner explicitly asks which security workflow phase or agent role to select.
user-invocable: true
disable-model-invocation: true
---

# Security workflow overview

Read the [shared evidence contract](references/evidence-contract.md) and
[course scenario](../../../docs/SCENARIO.md). Help the learner select a phase without performing
all phases automatically. This skill is an explicitly invoked overview, not an orchestrator.

| Learner task | Select agent first | Invoke phase skill |
| --- | --- | --- |
| Investigate a candidate | Security Vulnerability Hunter | /lab-investigate |
| Model flow and prioritize | STRIDE Threat Modeler | /lab-threat-model |
| Plan and implement selected fix | Secure Remediation Engineer | /lab-remediate |
| Review current patch in a fresh chat | Secure Code Reviewer | /lab-review |

Selecting or reading a skill does not configure an agent, tools, or a model. Verify the role
and tools in the approved harness. Keep inline execution. If native invocation is unavailable,
use the rehearsed manual procedure described in [compatibility](../../../docs/COMPATIBILITY.md).
The four Local prompts are compatibility adapters with distinct names from these skills.

Do not load the whole source corpus or every reference. Each phase links the small set of
inputs it needs. Missing evidence, unsafe scope expansion, or unverified role permissions must
be surfaced before proceeding. Refer to the shared contract for acceptance and repair limits.
