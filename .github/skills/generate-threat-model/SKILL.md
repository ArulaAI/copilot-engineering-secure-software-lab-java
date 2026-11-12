---
name: generate-threat-model
description: "Stage 2. Turn findings into prioritized threats with STRIDE and blast radius, and choose the top 5 to mitigate."
argument-hint: "Optional: a note on priorities"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the lab fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **STRIDE Threat Modeler**, ask the
learner to switch and stop.

# Stage 2: Threat model, blast radius and top 5

## Inputs

`outputs/handoff.md`, `outputs/codebase-map.md` (architecture and trust boundaries) and
`outputs/findings.md`. Open source only to confirm evidence.

## Procedure

1. Group findings an attacker would exploit the same way into threats (T-01, T-02, ...).
2. For each threat: entry point, STRIDE category, impact and likelihood on the template's 1-3
   scales, priority = impact x likelihood, with evidence.
3. For the five highest priorities, fill the blast radius table: who can exploit it, what they
   reach, which action, whether it repeats, whether anyone would notice. Each answer cites code
   or says assumed or unknown.
4. Rank the top five with a one-sentence mitigation each. Mark SQLI-01 and PATH-01 from the
   scenario as fixed in this lab; mark the rest deferred. If either lab fix falls outside the
   top five, explain why in one line.
5. Fill [the threat-model template](../../../outputs/threat-model.template.md) and set
   `threats_total` and `top5`.

## Done when

The learner agrees with the top five. Tell the learner to switch to **Hand-off Recorder** and
run `/hand-off`. Do not edit files.
