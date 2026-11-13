---
name: review-security-fix
description: "Critique the named fix or perform a final cumulative review."
argument-hint: "Review prompt, task name, or final"
user-invocable: true
disable-model-invocation: true
---

Run with **[Secure Code Reviewer](../../agents/secure-code-reviewer.agent.md)** selected in the agent picker, then invoke `/review-security-fix`. The skill runs in the selected agent context; it does not switch agents.

Use Secure Code Reviewer in a fresh chat. Resolve the requested scope from the prompt, guide and
actual changes. No internal ID, saved plan/ledger, prior gate or formal deferral is required.

Choose ONE mode. If the user requests final review/closeout, use Final closeout only; do not first
repeat the task-critique procedure.

## Task critique

1. Inspect the named task's source/test diff, including new files, against its requested criteria.
   Check root cause, legitimate behavior and clear regressions.
2. Independently run focused security/behavior tests and ordinary regression once. Add a detecting
   test only for a concrete in-scope gap, then run it. No new full-service audit or optional hardening.
3. Start the final response with **Verdict: PASS / PARTIALLY FIXED / FAIL / NOT VERIFIED**, choosing one status using the reviewer profile definitions, then actual commands/results and specific required changes.
   Use [the task format](templates/review.template.md). Append one short task result to outputs/review.md and stop. Return defects to Create through the
   participant; never fix application source, start another agent or select another task.

## Final security review report (Stage 4)

1. Read original findings, threat-model priorities, existing task critiques and the actual current
   application/supporting-file diff, including new files. Review all modified files and their affected
   interactions against the requested criteria. Summarize the application's known remaining risks
   from existing findings without a new whole-service scan or new implementation work.
2. Check current build/test evidence. Reuse matching accepted results when no relevant code changed,
   explicitly stating reuse; otherwise run the needed regression and missing relevant checks once.
   Do not rerun a separate clean compile just to duplicate compilation already established by Maven
   test. No new tests, red-team campaign, dependencies or service startup in final mode.
3. Generate ONE standalone report at outputs/security-review.md using
   [the final report structure](templates/security-review.template.md). Start with the
   verdict, then Executive Summary and Scope, Vulnerabilities Fixed, Remaining Issues and Required
   Changes, ASVS Gaps and Production Assurance, Build Status and Test Evidence, Final Decision and
   Next Steps. Fill with actual source/results; state missing evidence instead of inventing it.
4. Keep statuses consistent with original finding/threat IDs and requested scope. Explain partial
   parent-threat coverage. Separate blockers for completed scope from unstarted tasks. Only cite ASVS
   requirement IDs from a supplied versioned source; otherwise discuss control-area gaps and state
   no formal ASVS assessment occurred. No blanket compliance or deployment-security claim.
5. Give a short verdict-first chat response with test totals (passed excludes skipped), required
   corrections and the report path. Stop. Do not append another final summary to review.md, generate
   extra reports, validate metadata or auto-start another agent/task. Stage 3 review.md stays the
   concise task-critique input; this report is the consolidated final deliverable.

## Limits for both modes

Never weaken assertions, install dependencies, change the POM, start the service or make real external
requests. Missing relevant evidence stays NOT VERIFIED. Record blocked Spring integration once;
method-level proof is not full integration proof. Existing unselected seeded checks remain open.
No tasks.md/change-plan.md/task-reviews.md, cycle counters, artifact validators, metadata repair,
repeated hashes or per-cycle logs. Leave old artifacts untouched. Generic handoff remains separate.

## Verdict-first response

The FINAL review response and each saved review entry start with **Verdict: <STATUS>** and one
sentence explaining it. Use exactly one of:
- PASS: all requested criteria have adequate passing evidence, with no concrete in-scope gap.
- PARTIALLY FIXED: some requested controls are verified, but a required part remains incomplete.
- FAIL: the requested fix is ineffective or introduces a serious regression.
- NOT VERIFIED: available execution/evidence cannot establish the requested behavior.

Only PASS accepts the reviewed scope. Passing unit tests cannot override a known missing required
integration. After the verdict, give actual tests (total/passed/failed/skipped), then required changes
with code/test references, then remaining limits. Keep chat concise. Task mode appends one short review.md entry; final mode produces the single structured final report, not additional forms. Do not
announce a verdict before the review has actually completed. Optional follow-up is separate from
required corrections. Final closeout uses the same statuses for completed scope, not unstarted work.
