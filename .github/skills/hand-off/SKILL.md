---
name: hand-off
description: "End of every stage. Save the stage report to outputs/ and append the stage hand-off to outputs/handoff.md."
argument-hint: "Stage, e.g. 1a, 1b, 2, 3 or 4"
user-invocable: true
disable-model-invocation: true
---

Run with **Hand-off Recorder** selected, in the same chat as the stage that just finished, so
the stage's conversation is available. If another agent is selected, ask the learner to switch and stop.

# Hand-off

1. Identify the stage from the argument or the conversation: 1a codebase map, 1b findings,
   2 threat model, 3 change plan, 4 review.
2. If that stage's `outputs/` file still matches its template, write the report from the chat
   into it. Keep the template's header keys and tables. Copy values exactly; add nothing new.
   The learner sets `gate` after checking the gate list; leave it empty if they have not.
3. If `outputs/handoff.md` does not exist, create it from
   [the hand-off template](../../../outputs/handoff.template.md). Append one section for this
   stage in that format, under 12 lines, pointing to files instead of repeating them.
   Update `last_stage` in its header.
4. List the files you wrote and tell the learner to start a new chat for the next stage
   (after Stage 3, stay in this chat and switch back to Secure Remediation Engineer for Stage 4).

Edit only files under `outputs/`.
