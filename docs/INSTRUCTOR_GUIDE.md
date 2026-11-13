# Instructor guide

SAY is suggested framing. DO is an action. WATCH FOR is a misconception. CHECK-IN is a checkpoint.

## Before delivery

DO pin the release and record its source state. Complete COMPATIBILITY.md, VERIFICATION.md and
WORKFLOW_REHEARSAL.md. Use the private facilitator risk inventory outside the participant workspace;
no answer-key architecture is required. Recognize semantically equivalent findings, not exact AI titles.
DO rehearse one task end to end and a second-task transition, including task-specific tests, fresh
critique, three-cycle persistence, explicit handoff and final partial-scope closeout. Choose a live
scope based on observed timing. Keep dependency/POM and environment constraints intact.

## 0 to 10: Setup and demonstration

SAY You inherited this service. Understand it, prioritize risks, prove a change and challenge it.
DO demonstrate role selection, explicit skill, report inspection, gate and same-chat Hand-off Recorder
with /hand-off. Show that hand-off records state (even with a pending gate) and that the next stage
checks readiness. Show the distinction between handoff and task state. No repeated learner summaries.
CHECK-IN baseline succeeds with expected skipped seeded checks; customization discovery is qualified.

## 10 to 22: Map

DO inspect actual entry points and call edges. Package Explorer is optional where qualified; sequential
mapping is the fallback. WATCH FOR helpers drawn as connected controllers without call evidence.
CHECK-IN map labels implemented, assumed and proposed components/edges and names unknowns.

## 22 to 35: Investigate

DO compare evidence, preconditions and impact. AI may miss or group findings differently.
WATCH FOR finding quotas, simulated sinks reported as executed exploits and invented authentication policy.
CHECK-IN original findings saved, severity explained, a false positive rejected and coverage stated.
FLEX shorten discussion of lower-priority findings; do not force participants to inspect every row live.

## 35 to 50: Model and prioritize

DO apply STRIDE to all findings; assess severity, impact, likelihood and blast radius. Confirm the five
priorities with acceptance criteria. Do not prescribe issue types or assume all five are Critical.
CHECK-IN selected threats trace to actual findings and source evidence, with uncertainty retained.
FLEX use concise scoring explanations after a worked example, preserving a rationale for each priority.

## 50 to 70: Create and critique

DO choose how many tasks to attempt live, normally one or two; all priorities remain available.
For each task, agree scope, persist Cycle before Create, add relevant behavior/security tests, execute
checks, and critique in a fresh reviewer chat. Return concrete critique to the preserved implementation
chat. Three complete cycles maximum per task, including initial Create; stop earlier on acceptance.
CHECK-IN no next-task advancement before verified, previous fixes stay in regression and task histories
survive new chats. The reviewer can add tests and reports, but cannot fix application source.
WATCH FOR test weakening, self-certified acceptance, resetting cycles, or requiring irrelevant seeded
failures to turn green before another task is accepted. Relevant skips are missing proof, not passes.
DO pause unresolved third-cycle work. At time checkpoint explicitly defer remaining live work with
reasons; do not erase an unresolved defect. Close Stage 3 using the separate /hand-off action.

## 70 to 85: Final review, NEVER CUT

DO invoke /review-security-fix final in a fresh reviewer chat. Compare every original finding with
current source, all actual changes and current runs. Inspect cross-fix regressions and new files.
CHECK-IN acceptance is for verified completed scope, with deferred/unresolved tasks and untested
boundaries explicit. Return regressions to the affected task's remaining budget; no reset to pass.
DO inspect the final gate and run /hand-off with Recorder in this review chat.

## 85 to 90: Debrief, NEVER CUT

Ask what changed after source/test inspection, what critique caught, and what remains to be done.
SAY Take the evidence trail and the remaining priorities back to your own work.

## Recovery

If mapping or discussion overruns, narrow live implementation scope. If a task fails at the cycle
limit, preserve the result and pause for facilitator intervention. A failed demonstration is not a
verified fix; final review can honestly report incomplete scope. No guarantee of five fixes in 90 minutes.
Skills missing: qualify existing-file manual route. Commands blocked: participant runs the same check
and attaches its evidence by path. Dependency errors: approved support route, no bypass. Never reset
participant work or install tools during the session to recover a schedule.
