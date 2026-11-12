---
name: map-codebase
description: "Stage 1A. Map an unfamiliar codebase: index, entry points, data stores, trust boundaries and an architecture diagram."
argument-hint: "Optional: a package to start with"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the assigned fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Codebase Mapper**, ask the
learner to switch and stop.

# Stage 1A: Map the codebase

1. List the packages under `src/main/java`. Ignore `target/` and `reference/`.
2. Call **Package Explorer** as a subagent once per package, in parallel where possible. Pass
   only the package path. If subagents are unavailable, read each package yourself, one at a
   time, and say so in the hand-off.
3. From the summaries, fill the codebase map: index, entry points with untrusted inputs, data
   stores and external calls, trust boundaries, and the Mermaid architecture diagram with
   implemented, assumed or proposed labels.
4. Open the source for each entry point you list and confirm the file and line.
5. Set `packages_mapped` and `entry_points`.

Do not judge vulnerabilities in this stage. Note anything suspicious under Unknowns for Stage 1B.

## Write the report and the hand-off

1. Write `outputs/codebase-map.md`. If it does not exist, create it from
   [its template](../../../outputs/codebase-map.template.md); if it exists, update it. Keep the template's
   header keys and tables. Leave `gate` empty: the learner sets it after checking the gate list.
2. Append one section for this stage to `outputs/handoff.md` (create it from
   [the hand-off template](../../../outputs/handoff.template.md) if missing), under 12 lines,
   pointing to files rather than repeating them. Set `last_stage: 1a-map`.
3. Tell the learner which files you wrote, to check the gate list and set `gate: pass` or
   `gate: fail` in the report, then start a new chat with **Security Vulnerability Hunter** and `/find-vulnerabilities`.
