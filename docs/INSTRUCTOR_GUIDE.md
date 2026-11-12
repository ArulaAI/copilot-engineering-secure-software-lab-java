# Instructor guide

## Marker legend

SAY is suggested framing. DO is a facilitator action. WATCH FOR identifies a misconception.
CHECK-IN is a participant checkpoint. TIP is a delivery option.

## Before delivery

DO pin the release SHA and complete docs/COMPATIBILITY.md for each supported environment.
DO run the baseline, the reproduce command (six failures) and acceptance with the reference
solutions using docs/VERIFICATION.md. Check Maven artifacts using the organization's approved route.
DO confirm repository access, the approved model, chat permissions, and breakout-room support.
DO copy the six report templates (five stage reports plus handoff). Confirm role selection, phase skill discovery,
and that agents can read outputs/evidence/. Use docs/WORKFLOW_REHEARSAL.md to record time, quality,
corrective turns, and usage where exposed. Do not claim savings from the format change alone.
DO assign one facilitator to the parallel help room during the introduction.
DO validate the 90-minute rehearsal. Environment readiness is a separate gate from code checks.

Facilitator resources in reference/ are visible in the public clone. Explain the educational
boundary. For an independent assessment, distribute a participant package without reference/
and facilitator docs. Do not claim a prompt restriction prevents access to public solutions.

## 0 to 10 minutes. Setup and demonstration

SAY You have inherited a service nobody on the team wrote. We will map it before judging it,
rank before fixing, and attack our own fixes.
DO demonstrate the full rhythm: pick the agent, type the command from the / list, read the
report, tick the gate, switch to Hand-off Recorder in the same chat and type /hand-off, glance
at the saved report, then open a new chat. Learners never attach files or paste prompts.
SAY A hand-off is a short summary that points to files; open the files when you need detail.
TIP a prepared same-code comparison with and without its requirement/test can replace part of
this demonstration. Discuss evidence quality without adding required minutes or tooling.
DO show that `mvn -l` writes to outputs/evidence/ and prints nothing to the terminal.
CHECK-IN current baseline.txt matches the unchanged starter and shows 10 run, 6 skipped. Route setup issues to the help room.

## 10 to 22 minutes. Map the codebase

SAY Before asking what is wrong, ask what this code does and where outsiders get in.
DO point out the Package Explorer subagents: each package is read in its own context and only
a summary returns, which keeps the main chat small.
WATCH FOR vulnerability judgments in the map, or a database or authentication layer drawn as
implemented when the code only assumes it.
CHECK-IN codebase-map.md indexes all four packages, lists entry points with file and line,
and has a diagram with implemented and assumed labels.

## 22 to 35 minutes. Analyze

SAY Follow each untrusted input to where it is used. Nothing in the code labels the problems;
the code path is the evidence.
WATCH FOR finding-count competition, severity copied from comments, or duplicates not merged.
CHECK-IN findings.md is sorted by severity. Learners opened the top three locations, classified
candidates as confirmed/rejected/needs evidence, and rejected one false positive with evidence. Compare against reference/finding-catalog.md for coverage, not matching IDs.

## 35 to 50 minutes. Threat model, blast radius and top 5

SAY Priority is impact times likelihood. Blast radius is who, what, which action, how often,
and whether anyone would notice.
DO accept different top-5 rankings when the scores cite code. If SQLI-01 or PATH-01 falls
outside a learner's top five, ask for the one-line reason the template requires.
CHECK-IN threat-model.md has scored threats, five blast radius rows and a top-5 table marking
the lab fixes. See reference/threat-model.md for one reasonable answer.

## 50 to 70 minutes. Fix SQLI-01 and PATH-01

DO confirm the reproduce run shows six failing exploit tests before any edit.
DO require scope agreement before the first edit, and command approval for each run.
SAY Inspect the exact command before approval. This human checkpoint complements host permissions.
WATCH FOR edits to tests or pom.xml, fixes spreading to variant methods, or a copied solution.
CHECK-IN acceptance.txt shows 10 run, 0 skipped; fix.diff touches only UserRepository.java and
FileHandler.java; the Stage 3 hand-off is written and the chat is still open.

## 70 to 85 minutes. Verify, then red-team

DO confirm learners switched back to Secure Remediation Engineer in the Stage 3 chat before
running /review-security-fix. The reviewer runs as a subagent and reruns acceptance itself.
SAY Verify first. Red-team follows only after the assigned fixes pass verification. The reviewer
must rerun acceptance itself and inspect its log. Do not substitute the author's old result.
DO expect red-team to hold against a careful fix and to catch common naive ones (unescaped LIKE
wildcards, String.startsWith on paths). See reference/threat-model.md.
WATCH FOR red-team classes ending in Test (they join the acceptance count) or loops beyond one
correction.
CHECK-IN review.md has section A with evidence, a decision for every successful attack, a
verdict, and the final hand-off. This stage is NEVER CUT.

## 85 to 90 minutes. Debrief

DO ask the four debrief questions and walk the Copilot-safety table. NEVER CUT.
SAY On your own codebase: map it, rank it, fix the top items with tests, attack your fixes.

## Common room issues

| Symptom | Action |
| --- | --- |
| Slash command missing | Stop for facilitator, check discovery and qualify the existing-skill fallback |
| Different findings or severity | Compare evidence and preconditions, not raw counts |
| No report file | Check /hand-off ran in the stage's chat and inspect Recorder output |
| Subagent not called | Qualify sequential mapping or a new reviewer chat and record the route |
| Agent asks for pasted logs | Check the evidence path, source revision and missing/stale run before rerunning |
| Baseline green before fix | Show the skipped checks and enable security.verify |
| Model pinned/unavailable | Confirm approved selected model and reload repository agents |
| New package requested | Decline scope expansion and use existing Java/Maven features |
| Spring server fails | Required exercise uses tests. Record the runtime issue separately |
| Skill or role unavailable in IDEA | Attach the phase SKILL.md and send the one-line fallback; record the limitation |

## Solutions and residual scope

See reference/README.md for the reference solutions to SQLI-01 and PATH-01, the finding catalog
and an example threat model. The reference fixes change only the assigned methods; variants stay
vulnerable by design. No claims are made about production authentication, deployment security
or PCI compliance.
