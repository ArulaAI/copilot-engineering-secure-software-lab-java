---
name: generate-threat-model
description: "Stage 2. Turn findings into prioritized threats with STRIDE and blast radius, and choose the top 5 to mitigate."
argument-hint: "Optional: a note on priorities"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the assigned fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **STRIDE Threat Modeler**, ask the
learner to switch and stop.

# Stage 2: Threat model, blast radius and top 5

Inputs: `outputs/handoff.md`, `outputs/codebase-map.md` (architecture and trust boundaries) and
`outputs/findings.md`. Open source only to confirm evidence.

1. Group findings an attacker would exploit the same way into threats (T-01, T-02, ...).
2. For each threat: entry point, STRIDE category, impact and likelihood on the template's 1-3
   scales, priority = impact x likelihood, with evidence.
3. For the five highest priorities, fill the blast radius table: who can exploit it, what they
   reach, which action, whether it repeats, whether anyone would notice. Each answer cites code
   or says assumed or unknown.
4. Rank the top five with a one-sentence mitigation each. Mark SQLI-01 and PATH-01 from the
   scenario as fixed in this lab and the rest deferred. If either lab fix falls outside the top
   five, explain why in one line.
5. Set `threats_total` and `top5`.

## Write the report and the hand-off

1. Write `outputs/threat-model.md`. If it does not exist, create it from
   [its template](../../../outputs/threat-model.template.md); if it exists, update it. Keep the template's
   header keys and tables. Leave `gate` empty: the learner sets it after checking the gate list.
2. Append one section for this stage to `outputs/handoff.md` (create it from
   [the hand-off template](../../../outputs/handoff.template.md) if missing), under 12 lines,
   pointing to files rather than repeating them. Set `last_stage: 2-threat-model`.
3. Tell the learner which files you wrote, to check the gate list and set `gate: pass` or
   `gate: fail` in the report, then start a new chat with **Secure Remediation Engineer** and `/fix-vulnerability`.
