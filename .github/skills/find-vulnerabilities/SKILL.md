---
name: find-vulnerabilities
description: "Stage 1B. Analyze the mapped codebase for security findings and rank them by severity."
argument-hint: "Optional: a package to focus on"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the lab fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Security Vulnerability Hunter**, ask the
learner to switch and stop.

# Stage 1B: Analyze

## Inputs

`outputs/codebase-map.md` and `outputs/handoff.md`, then the source they point to.

## Procedure

1. Work through the entry points in the map. Follow each untrusted input to where it is used:
   SQL, file paths, URLs, processes, deserialization, authentication and authorization
   decisions, secrets and cryptography.
2. Open each location before reporting it. Cite file, method and current line.
3. Merge symptoms with one root cause into one finding with several locations.
4. Assign severity (Critical, High, Medium, Low) from the exploit path and impact. A comment
   saying "vulnerable" is not evidence; the code path is.
5. Reject at least one plausible false positive with evidence.
6. Fill [the findings template](../../../outputs/findings.template.md), sorted by severity, and
   set `findings_total` and `top_severity`.

## Done when

The learner has opened the top three findings' locations and agrees with their severity. Tell
the learner to switch to **Hand-off Recorder** and run `/hand-off`. Do not edit files.
