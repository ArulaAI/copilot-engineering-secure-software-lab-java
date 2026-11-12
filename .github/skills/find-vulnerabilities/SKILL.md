---
name: find-vulnerabilities
description: "Stage 1B. Analyze the mapped codebase for security findings and rank them by severity."
argument-hint: "Optional: a package to focus on"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the assigned fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Security Vulnerability Hunter**, ask the
learner to switch and stop.

# Stage 1B: Analyze

Inputs: `outputs/codebase-map.md` and `outputs/handoff.md`, then the source they point to.

1. Work through the entry points in the map. Follow each untrusted input to where it is used:
   SQL, file paths, URLs, processes, deserialization, authentication and authorization
   decisions, secrets and cryptography.
2. Open each location before reporting it. Cite file, method and current line.
3. Merge symptoms with one root cause into one finding with several locations.
4. Assign severity (Critical, High, Medium, Low) from the exploit path and impact. A comment
   saying "vulnerable" is not evidence; the code path is.
5. Mark each finding confirmed or needs evidence, and say what evidence is missing for the second.
   Reject at least one plausible false positive with evidence.
6. Sort by severity and set `findings_total` and `top_severity`.

## Write the report and the hand-off

1. Write `outputs/findings.md`. If it does not exist, create it from
   [its template](../../../outputs/findings.template.md); if it exists, update it. Keep the template's
   header keys and tables. Leave `gate` empty: the learner sets it after checking the gate list.
2. Append one section for this stage to `outputs/handoff.md` (create it from
   [the hand-off template](../../../outputs/handoff.template.md) if missing), under 12 lines,
   pointing to files rather than repeating them. Set `last_stage: 1b-analyze`.
3. Tell the learner which files you wrote, to check the gate list and set `gate: pass` or
   `gate: fail` in the report, then open the locations of the top three findings, then start a new chat with **STRIDE Threat Modeler** and `/generate-threat-model`.
