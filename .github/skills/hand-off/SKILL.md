---
name: hand-off
description: "Capture the current state of work for continuation in a new chat or session. Use at any point, in any repository; it never blocks."
argument-hint: "Optional: what this hand-off covers, or a file path to write to"
user-invocable: true
disable-model-invocation: true
---

Use with **Hand-off Recorder**. If another agent is selected, ask the user to switch; that is the
only reason to stop. Running in the same chat as the work gives the best record, but if the earlier
conversation is not available, record that and capture what the files and Git show.

# Hand-off

1. Work out what to cover from the argument, the conversation, or recent changes. Do not ask the
   user to restate it.
2. Read the existing hand-off file if there is one, plus any reports, task lists or notes the work
   produced. Inspect `git status`, `git diff --stat`, untracked files and the current revision.
3. Record the state as it is, including anything unfinished, failed or awaiting a decision. An unset
   gate or missing approval is written down as pending, not treated as an error.
4. Write one new section to the hand-off file (see the recorder's "Where to write"), following the
   repository's hand-off template if one exists. Keep it short and point to files rather than
   copying them. Add a date or revision so sections can be told apart.
5. Show the user the section you wrote and the file path. Do not decide whether the next stage may
   start; that is for the next stage's own checks and the user.
