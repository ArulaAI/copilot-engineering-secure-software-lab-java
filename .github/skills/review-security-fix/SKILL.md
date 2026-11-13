---
name: review-security-fix
description: "Critique the named fix or perform a final cumulative review."
argument-hint: "Review prompt, task name, or final"
user-invocable: true
disable-model-invocation: true
---

Use Secure Code Reviewer in a fresh chat. Resolve the requested scope from the prompt, guide and
actual changes. No internal ID, saved plan/ledger, prior gate or formal deferral is required.

Choose ONE mode. If the user requests final review/closeout, use Final closeout only; do not first
repeat the task-critique procedure.

## Task critique

1. Inspect the named task's source/test diff, including new files, against its requested criteria.
   Check root cause, legitimate behavior and clear regressions.
2. Independently run focused security/behavior tests and ordinary regression once. Add a detecting
   test only for a concrete in-scope gap, then run it. No new full-service audit or optional hardening.
3. Give ACCEPTED, CHANGES REQUIRED or NEEDS EVIDENCE with actual commands/results and specific gaps.
   Append one short task result to outputs/review.md and stop. Return defects to Create through the
   participant; never fix application source, start another agent or select another task.

## Final closeout (Stage 4)

1. Read the existing review results and current change summary. Confirm the completed fixes are
   the ones requested and note any changes since the last accepted critique. Do not rescan the whole
   service or start a new red-team exercise. Unstarted findings remain open, not new assignments.
2. Check completed-scope regression evidence. If the latest accepted critique already ran the same
   relevant checks on the current unchanged code, reuse that result and state it was reused. Otherwise
   run one appropriate regression command and only relevant checks missing from it. Do not collect
   hashes or metadata solely to prove reuse; use the actual intervening changes and run record. If
   matching evidence is uncertain, execute the required check once and report the uncertainty.
3. Write one short final summary in outputs/review.md: fixes completed, actual/reused tests and
   relevant skips, verdict, open issues and unverified boundaries. Do not generate another report,
   tests or mandatory form. A real regression is CHANGES REQUIRED, not an invitation to fix more
   service weaknesses. Stop; any further Create is a participant/facilitator decision.

## Limits for both modes

Never weaken assertions, install dependencies, change the POM, start the service or make real external
requests. Missing relevant evidence stays NEEDS EVIDENCE. Record blocked Spring integration once;
method-level proof is not full integration proof. Existing unselected seeded checks remain open.
No tasks.md/change-plan.md/task-reviews.md, cycle counters, artifact validators, metadata repair,
repeated hashes or per-cycle logs. Leave old artifacts untouched. Generic handoff remains separate.
