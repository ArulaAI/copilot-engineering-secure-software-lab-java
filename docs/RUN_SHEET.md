# Facilitator run sheet

| Clock | Stage | Cue |
| --- | --- | --- |
| 0 to 10 | 0 Setup and demo | Rhythm: new chat, pick agent, type command, check report. Baseline 10 run, 6 skipped |
| 10 to 22 | 1A Map | Package Explorer subagents per package. Four packages indexed; entry points cite lines |
| 22 to 35 | 1B Analyze | Ranked Critical to Low; confirmed or needs evidence. Open the top three |
| 35 to 50 | 2 Threat model and top 5 | Impact x likelihood with evidence. Blast radius for the top 5 |
| 50 to 70 | 3 Fix | Six exploit tests fail first. Agree scope. Approve each command. 10 run, 0 skipped |
| 70 to 85 | 4 Review and red-team | New chat. Reviewer reruns tests, verifies, then attacks. NEVER CUT |
| 85 to 90 | 5 Debrief | Three questions and the Copilot-safety table. NEVER CUT |

Commands are in docs/SCENARIO.md. Each stage's agent writes its own report in outputs/ and adds
a short section to outputs/handoff.md, so learners never paste prompts or logs and never switch
agents mid-stage.

Limits: one correction pass and one re-review, then escalate. No red-team before verification.
`mvn -Dtest=OutputsCheck test` shows which learners have complete report forms.
Assessment aids: reference/finding-catalog.md and reference/threat-model.md. Platform limits:
docs/COMPATIBILITY.md.

If findings differ, compare evidence and reasoning, never counts. If asked whether the
application is secure, point to the deferred items in their top 5.

Closing line: map before you judge, rank before you fix, prove the bug, attack your own fix.
