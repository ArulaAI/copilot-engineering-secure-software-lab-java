---
name: generate-threat-model
description: Model the selected refund flow and connect threats to acceptance.
agent: STRIDE Threat Modeler
---

Read docs/SCENARIO.md and the selected findings supplied in this conversation.
Inspect the refund implementation. Model the Principal fixture, refund service, and
in-memory payment state. Label implementation evidence and assumptions. Apply STRIDE
to relevant boundaries, explain impact and exploit preconditions, and prioritize REFUND-01.
Return outputs/threat-model.template.md content in chat. Preserve the finding ID.
