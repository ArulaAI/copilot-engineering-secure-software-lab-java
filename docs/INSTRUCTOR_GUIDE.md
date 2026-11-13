# Instructor guide: 60-minute hands-on route

Before delivery, rehearse the exact release, IDE/plugin/model and one chosen assignment. Complete
COMPATIBILITY.md and VERIFICATION.md. Known-risk material stays outside the participant workspace.

| Clock | Action |
| --- | --- |
| 0 to 5 | Baseline and demonstrate role/skill selection |
| 5 to 10 | Map source and configuration; inspect actual connections |
| 10 to 18 | Investigate whole-service findings; separate defects and reachability |
| 18 to 28 | STRIDE, scenario prerequisites, blast radius and top five |
| 28 to 50 | One main named task: Create, tests, fresh Critique; another only if time permits |
| 50 to 57 | Final cumulative review; retain remaining priorities |
| 57 to 60 | Debrief on evidence, critique and follow-up |

SAY The implementation prompt is the assignment. The agent briefly explains, fixes and tests; no
separate plan or form approval. The participant then sends the review prompt in a fresh reviewer chat.
DO inspect real code/test results. A reviewer must give concrete in-scope gaps, not expand into a
new full-service audit. Correct and review as needed, with three attempts as a facilitator guideline.
WATCH FOR tasks/plan metadata, repeated log/hash/validator work, automatic next-task selection and
extra agent delegation. Interrupt these: they are not part of this lean workflow.
DO leave old rehearsal reports/code/tests alone. Do not discard work to simplify bookkeeping.
DO keep test assertions and dependencies intact. A blocked Spring context is one recorded limitation,
not a reason to repeatedly try integration harnesses or claim HTTP proof. No new packages or external probes.
CHECK-IN a concise review gives changed scope, actual tests/results, verdict and remaining issues.
Unstarted tasks remain open without a deferral question. Final review is about completed changes,
not fixing every priority. Generic handoff captures what exists whenever requested.

Shorten discussion or live task count if over time. Preserve meaningful tests and final review.
Broad authentication/payment tasks still need a timed rehearsal; the reduced process does not prove
that every assignment fits. No guaranteed speed/token improvement is claimed before measuring it.

## Stage 4: optional verbal discussion, not agent work

DO run the short final-closeout prompt. Reuse current accepted tests where applicable; run a missing
regression check once. Do not start another audit or ask the agent to implement the remaining priorities.

SAY We have evidence for the changes we completed. We have not certified this whole service. The
remaining findings and runtime limits are still open; a green unit-test run is not a deployment review.

If time allows, discuss these extensions without executing them:

- **Production integration:** verify real request authentication, authorization, persistence and
  deployment configuration. Method-level checks cannot establish every integration boundary.
- **Broader security assurance:** use versioned ASVS requirements, approved SAST/dependency scanning,
  fuzzing or specialist review when the production scope warrants them. None is required to finish this lab.
- **Operational controls:** protect secrets, monitor abuse, define safe logging and review deployment
  permissions. AI-assisted review complements these controls rather than replacing them.

ASK Which additional check would matter most before you deployed this particular fix? Discuss one
example; do not turn the answer into another implementation assignment during closeout.
