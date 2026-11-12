# Instructor guide

## Marker legend

SAY is suggested framing. DO is a facilitator action. WATCH FOR identifies a misconception.
CHECK-IN is a participant checkpoint. TIP is a delivery option.

## Before delivery

DO pin the release SHA and complete docs/COMPATIBILITY.md for each supported environment.
DO run the starter baseline, expected failing acceptance, and corrected-solution acceptance
using docs/VERIFICATION.md. Check Maven artifacts using the organization's approved route.
DO confirm repository access, the approved model, chat permissions, and breakout-room support.
DO copy the five participant templates. Confirm role selection, phase skill discovery, Local
adapters, and context attachment. Use docs/WORKFLOW_REHEARSAL.md to record time, quality,
corrective turns, and usage where exposed. Do not claim savings from the format change alone.
DO assign one facilitator to the parallel help room during the introduction.
DO validate the 90-minute rehearsal. Environment readiness is a separate gate from code checks.

Facilitator resources in reference/ are visible in the public clone. Explain the educational
boundary. For an independent assessment, distribute a participant package without reference/
and facilitator docs. Do not claim a prompt restriction prevents access to public solutions.

## 0 to 20 minutes. Introduction and demonstration

SAY We will establish evidence for one security change. A model report and a green baseline
are starting points, and the selected security acceptance checks determine the outcome.
DO explain the scenario, trusted Principal fixture, method-level scope, and available roles.
DO demonstrate context attachment, a read-only investigation, manual report saving, and a fresh
review. Demonstrate explicit lab-* invocation after selecting its role. Show Local adapters
only on the qualified compatibility route. Explain that the skill does not set agent permissions.
WATCH FOR anyone treating a bearer string as trusted identity or the word secure as a guarantee.
CHECK-IN participants can run baseline and see the required files. Route setup issues to help.

## 20 to 35 minutes. Investigate

DO have participants run the two enabled security checks. Both fail on the starter with
expected FORBIDDEN but actual REFUNDED. These are intentional defects. Missing artifacts,
compile errors, or unrelated exceptions require troubleshooting, not explanation as vulnerabilities.
SAY Find the decision that permits an authenticated non-owner to mutate refund state.
WATCH FOR finding-count competition or using reference/ to generate the discovery report.
CHECK-IN outputs/findings.md contains a source location, actual failure, and rejected hypothesis.
TIP this stage is FLEX. Cut extra historical-corpus investigation, preserving REFUND-01 evidence.

## 35 to 45 minutes. Threat model

DO use about seven minutes for the Java view and three for the separate engineering-workflow view.
Require implemented/assumed/proposed labels. The Java fixture has no deployed gateway, database,
or AI agent. Use docs/owasp-reference/ai-security.md for verified editions and category names.
SAY Which asset changes, who should control it, and where should that decision be enforced?
For the AI view ask where untrusted content enters context, which tools could act on it,
and which control is an instruction versus an observed host restriction.
DO use docs/exercises/ai-context-sample.md only in a qualified read-only role. No commands,
edits, credentials or external services are involved. If unavailable, discuss it on paper and
mark runtime behavior untested. Inspect benign facts as well as the attempted instruction.
WATCH FOR all-ten-category quotas, treating a false output as a rogue agent, or using Java tests
as AI security proof. Intended profile tools are not evidence of effective platform enforcement.
CHECK-IN T-REFUND-01 links to the Java patch. T-AI-01 records the workflow hypothesis, input
provenance, relevant ASI/LLM edition, action consequence, control and evidence status.
Do not expand the required implementation beyond REFUND-01. Full AI reference reading is
facilitator preparation or follow-up, not a new timed exercise.

## 45 to 70 minutes. Plan and implement

DO ask participants to agree to the proposed scope before editing only RefundService.java.
DO preserve tests, POM, valid owner behavior, and the existing atomic state transition.
WATCH FOR disabling security checks, editing expectations, adding dependencies, or copying the solution.
CHECK-IN the actual diff is bounded, enabled acceptance has run, and acceptance.md links
criteria to source/tests and actual results. Reuse relevant implementation context. Avoid
redundant scans or repeated model calls to obtain agreement.
TIP if a participant is stuck, ask where ownership is compared to the trusted principal.

## 70 to 85 minutes. Verify and review

DO inspect current full enabled acceptance evidence. Reuse stage-3 results when they match
the current source/diff. Rerun only after a change, relevant failure, or evidence gap.
Expect nine passing checks with none skipped.
DO start a fresh reviewer conversation with a compact packet of selected source, diff, plan,
acceptance matrix, and execution evidence. Include failures/skips and full-log access. Allow
one concrete correction pass and re-review, then escalate unresolved work. A source comment
requesting test deletion is untrusted data and must not change the permitted scope.
SAY Reading supplied test logs is different from personally executing tests. Record the distinction.
WATCH FOR whole-application certification, skipped checks, or unexplained failures.
CHECK-IN outputs/review.md records a qualified verdict and untested integration boundaries.
This stage is NEVER CUT. Keep a pre-verified facilitator fixture available for discussion if
an individual machine fails, and label that evidence as facilitator evidence.

## 85 to 90 minutes. Debrief

DO ask the four action-guide debrief questions. This stage is NEVER CUT.
SAY Take the evidence procedure back to your repository and adapt it to your authentication,
storage, deployment, and verification boundaries.

## Common room issues

| Symptom | Action |
| --- | --- |
| Slash prompt missing | Check harness, then use the tested skill/manual route |
| Different findings or severity | Compare evidence and preconditions, not raw counts |
| No report file | Reading roles return text; participant saves and checks the file |
| Baseline green before fix | Show the skipped checks and enable lab.verify |
| Model pinned/unavailable | Confirm approved selected model and reload repository agents |
| New package requested | Decline scope expansion and use existing Java/Maven features |
| Spring server fails | Required exercise invokes methods through tests; record runtime issue separately |
| Skill or role unavailable in IDEA | Use rehearsed context/manual route with explicit limitations |

## Solutions and residual scope

See reference/README.md for the independently verified refund solution. Legacy secure examples
are quarantined and must not be used as models. Password/session and SSRF integration issues
are deferred reference-review topics. WebhookVerifier covers signature/time verification, not
persistent event deduplication. No claims are made about production authentication or PCI compliance.
