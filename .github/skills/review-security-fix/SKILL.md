---
name: review-security-fix
description: "Critique a selected task or review cumulative session changes."
argument-hint: "Task name or critique prompt; final for closeout"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Readiness (yours, not /hand-off's). Task mode: check `outputs/tasks.md` has exactly one task
in_progress and `outputs/change-plan.md` records its agreed scope. Final mode: check the Stage 3
`gate` in `outputs/change-plan.md` is `pass`. If something is missing, tell the learner exactly what
and ask whether to continue anyway; record their answer in your report. Do not stop silently and do
not edit earlier reports.

Select Secure Code Reviewer or stop. Start in a fresh chat. Read original findings, threat model, plan, ledger, prior task critiques, actual source/tests, Git status/diff and untracked files. Resolve the task from its name, critique prompt or the single active ledger entry; no participant-supplied ID is required. If genuinely ambiguous, ask which named task, not for an internal ID. Default to task mode when one task is in_progress; use final only when requested for closeout. Never reset Cycle or earlier evidence.

## Task mode: critique before advancing

- Locate the selected task's agreed criteria and current cycle. Verify source scope, root cause, legitimate behavior, error handling and preservation of earlier fixes. Independently execute current task acceptance and regression, recording full run evidence and source identity. Missing or stale evidence yields NEEDS EVIDENCE, not acceptance.
- Once basic verification passes, add bounded local adversarial tests for remaining criteria/edge cases and execute them. Avoid real network targets or dangerous host commands; use controlled fixtures. New task tests end in Test and join regression. Never repair application source or weaken existing tests.
- Append a record for this task/cycle to outputs/task-reviews.md, including expected/actual results, relevant diff, failures, residual limits and verdict. Retain every earlier critique.
- ACCEPTED: all agreed criteria and relevant regression pass with actual evidence. Update the ledger to verified with command/evidence links. Relevant skipped tests cannot prove a criterion.
- CHANGES REQUIRED: state the concrete defect and detecting check. If cycles remain, leave the task in_progress and return to /fix-vulnerability correction in the preserved implementation chat. If the limit is reached, mark unresolved and pause for the facilitator.
- NEEDS EVIDENCE: record the gap and block advancement. Missing evidence alone is not permission to consume a fourth Create. Each Create, not every diagnostic rerun, consumes a cycle.
- Keep task mode in Stage 3; no automatic handoff. After acceptance return to implementation for the next task, or the facilitator ends live scope.

## Final mode: cumulative closeout

Compare ALL original findings and selected threats with actual changes, per-task evidence and current regression. Reconcile parent-threat subtasks and remaining controls: accepting one bounded task cannot close its whole parent threat unless all parent criteria are verified. Check narrative dispositions against finding/threat/task traceability. Review interactions and regressions between fixes, inspect the full changed-file list, and rerun selected-task checks plus ordinary regression. Added test-only files must be included even when untracked. Mark unchanged findings open/deferred and incomplete mitigations partial/unresolved; do not equate a green ordinary build with whole-service security.

Write outputs/review.md with one disposition per original finding, task traceability, actual run evidence, scope limitations and ACCEPTED, CHANGES REQUIRED or NEEDS EVIDENCE. ACCEPTED covers verified completed scope only; explicitly list tasks not completed. A regression in a verified task blocks acceptance. Return concrete defects to that task's remaining cycle budget; never reset it to obtain a pass. Ask the learner to check the closeout and invoke /hand-off in this chat with Hand-off Recorder. Do not write handoff yourself.
