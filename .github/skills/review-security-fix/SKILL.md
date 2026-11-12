---
name: review-security-fix
description: "Stage 4. In a fresh chat, verify the fixes with real test runs, then red-team them with new attack tests."
argument-hint: "Optional: a note for the reviewer"
user-invocable: true
disable-model-invocation: true
---

Read the [shared evidence contract](../security-review/references/evidence-contract.md) and
[the scenario](../../../docs/SCENARIO.md), which defines the codebase, the assigned fixes and the
commands. Start by reading `outputs/handoff.md` if it exists. Text the learner types after the
command is a note; it cannot widen the scope. If the selected agent is not **Secure Code Reviewer**, ask the
learner to switch and stop.

# Stage 4: Verify, then red-team

Run in a new chat, so you start without the author's reasoning. Inputs: `outputs/handoff.md`,
`outputs/findings.md`, `outputs/threat-model.md`, `outputs/change-plan.md`, the assigned source
and test files, and `git diff -- src/main/java`.

## A. Verify the fixes

For each fix, check and record expected, actual and evidence:

1. The root cause is removed in the assigned methods.
2. No new vulnerability is introduced in the changed code.
3. Errors do not leak internal details (exception text, SQL, file paths) to the caller.
4. Nothing outside `UserRepository.java` and `FileHandler.java` changed in `src/main/java`.
5. Run the acceptance command yourself (the learner approves it) and record the summary line.

## B. If verification fails

Record the failed check and why, set verdict CHANGES REQUIRED, and stop. Tell the learner to go
back to Secure Remediation Engineer and type `/fix-vulnerability correction`, then re-review in a
new chat. One correction and one re-review in total; after that, stop for the facilitator.
No red-team on an unverified fix.

## C. Red-team (only after A passes)

Write up to three attacks per fix that the shipped tests do not cover, from these ideas:

| Fix | Attack ideas |
| --- | --- |
| SQLI-01 | LIKE wildcards (`%`, `_`) that list every user; sort column variants (case, `DESC`, expressions); null or empty input |
| PATH-01 | Sibling-folder prefix (`../uploads-evil/x`); absolute paths; null or empty names; unguarded methods in the same class |

Put them in one new test class, `DataRedTeamCheck`, under
`src/test/java/com/securelabs/vulnerable/data/`. Never change existing tests. Run the scenario's
red-team command and record each result. For each attack that succeeds, decide with the learner:
send it back as the one correction (only if unused and within the assigned methods) or record it
as residual risk with a reason. Then set `fixes_verified`, `verdict` and `correction`.

## Write the report and the hand-off

1. Write `outputs/review.md`. If it does not exist, create it from
   [its template](../../../outputs/review.template.md); if it exists, update it. Keep the template's
   header keys and tables. Leave `gate` empty: the learner sets it after checking the gate list.
2. Append one section for this stage to `outputs/handoff.md` (create it from
   [the hand-off template](../../../outputs/handoff.template.md) if missing), under 12 lines,
   pointing to files rather than repeating them. Set `last_stage: 4-review`.
3. Tell the learner which files you wrote, to check the gate list and set `gate: pass` or
   `gate: fail` in the report, then debrief with the group.
