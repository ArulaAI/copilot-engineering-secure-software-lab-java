---
name: map-codebase
description: "Stage 1A. Map an unfamiliar codebase: index, entry points, data stores, trust boundaries and an architecture diagram."
argument-hint: "Optional: a package to start with"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the lab fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Codebase Mapper**, ask the
learner to switch and stop.

# Stage 1A: Map the codebase

## Procedure

1. List the packages under `src/main/java`. Ignore `target/` and `reference/`.
2. Call **Package Explorer** as a subagent once per package, in parallel where possible. Pass
   only the package path. If subagents are unavailable, read each package yourself, one at a time,
   and say so in the hand-off.
3. From the summaries, fill [the codebase-map template](../../../outputs/codebase-map.template.md):
   index, entry points with untrusted inputs, data stores and external calls, trust boundaries,
   and the Mermaid architecture diagram with implemented, assumed or proposed labels.
4. Open the source for each entry point you list and confirm the file and line.
5. Set `packages_mapped` and `entry_points`. Leave `gate` for the learner.

Do not judge vulnerabilities in this stage. Note anything suspicious under Unknowns for Stage 1B.

## Done when

The map is complete in chat. Tell the learner to check the gate, then switch to
**Hand-off Recorder** and run `/hand-off`.
