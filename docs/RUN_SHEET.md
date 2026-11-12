# Facilitator run sheet

| Clock | Stage | Cue |
| --- | --- | --- |
| 0 to 10 | Setup and demo | Agent, command, gate, `/hand-off`, new chat. Baseline 10 run, 6 skipped |
| 10 to 22 | 1A Map | Package Explorer subagents per package. Every package indexed; entry points cite lines |
| 22 to 35 | 1B Analyze | Findings ranked Critical to Low. Open the top three. Evidence is the code path |
| 35 to 50 | 2 Threat model and top 5 | Impact x likelihood with evidence. Blast radius for top 5. SQLI-01 and PATH-01 located |
| 50 to 70 | 3 Fix | Reproduce first (6 failures). Agree scope. Approve each command. Acceptance 10 run, 0 skipped |
| 70 to 85 | 4 Verify, then red-team | Same chat. Reviewer subagent verifies first. RedTeamCheck runs separately. NEVER CUT |
| 85 to 90 | Debrief | Four questions and the Copilot-safety table. NEVER CUT |

Commands are in docs/SCENARIO.md. Every run writes to outputs/evidence/ with `mvn -l`, which
prints nothing to the terminal.

Rhythm per stage: pick agent, type the command, pass the gate, switch to Hand-off Recorder and
type `/hand-off`, start a new chat. After Stage 3's hand-off, stay in that chat and switch back
to Secure Remediation Engineer for Stage 4. Learners never paste prompts or logs.

Limits: one correction pass and one re-review, then escalate. No red-team before verification.
`mvn -Dtest=OutputsCheck test` shows which learners have complete report forms.
Assessment aids: reference/finding-catalog.md and reference/threat-model.md.

If findings differ, compare evidence and reasoning, never counts. If asked whether the
application is secure, point to the deferred items in their top 5. Do not introduce
installations during the session.

Closing sentence: map before you judge, rank before you fix, and attack your own fix.
