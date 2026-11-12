---
name: review-security-fix
description: "Stage 4. Verify the fixes in an isolated reviewer subagent, then red-team them with new attack tests."
argument-hint: "Optional: a note for the reviewer"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the lab fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Secure Remediation Engineer**, ask the
learner to switch and stop.

# Stage 4: Verify, then red-team

This runs in the Stage 3 chat with **Secure Remediation Engineer** selected. That agent calls
**Secure Code Reviewer** as a subagent, so the review has isolated context.

## A. Verify the fixes (reviewer subagent)

Call Secure Code Reviewer with only the fix IDs (SQLI-01, PATH-01) and this file list:
`outputs/findings.md`, `outputs/threat-model.md`, `outputs/change-plan.md`,
`outputs/evidence/fix.diff`, and the assigned source and test files. Do not pass your reasoning.
The reviewer checks each fix removes its root cause, reruns the acceptance command itself, and
fills section A of [the review template](../../../outputs/review.template.md).

## B. If verification fails

The reviewer names the failed check and why. Fill section B. Make one correction pass for that
defect only, rerun acceptance and the diff, and call the reviewer once more. If it still fails,
set verdict CHANGES REQUIRED and stop for the facilitator. No red-team on an unverified fix.

## C. Red-team (only after A passes)

The reviewer proposes up to three attacks per fix that the shipped tests do not cover:

| Fix | Attack ideas |
| --- | --- |
| SQLI-01 | LIKE wildcards (`%`, `_`) that list every user; sort column variants (case, `DESC`, expressions); null or empty input |
| PATH-01 | Sibling-folder prefix (`../uploads-evil/x`); absolute paths; null or empty names; unguarded methods in the same class |

Write the chosen attacks into a new test class ending in `RedTeamCheck` under
`src/test/java/com/securelabs/vulnerable/data/`. Existing tests stay untouched. Run the
scenario's red-team command and read `outputs/evidence/redteam.txt`. Each attack the code fails
gets a decision: fixed (only within the assigned methods and the correction budget) or residual
risk with a reason. Fill section C.

## Done when

Sections A to C and the verdict are complete in chat. Tell the learner to switch to
**Hand-off Recorder** and run `/hand-off`.
