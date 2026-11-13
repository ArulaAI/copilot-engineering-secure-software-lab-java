---
name: security-review
description: "Explain the current workflow and next action without performing a stage."
argument-hint: "Optional workflow question"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Read the handoff and current task ledger, not only last_stage. Explain the next permitted action: mapping, investigation, threat modeling, one-task Create/critique, or final review. Each stage closes with explicit /hand-off; only Recorder writes it. A selected task must be verified before advancing, with a maximum of three complete cycles. The facilitator decides how many tasks to do live; there is no hardcoded implementation assignment or count. Do not perform a stage or alter state.
