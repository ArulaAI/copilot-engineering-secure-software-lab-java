# Lab Action Guide — Secure Software Development (Java)

You have inherited a legacy Java service nobody on the team wrote. With GitHub Copilot you will
map it, find and rank its security problems, decide what to mitigate first, fix two of them with
evidence, and attack your own fixes. The codebase, the two assigned fixes and every command are
in [docs/SCENARIO.md](docs/SCENARIO.md).

## How you drive Copilot

Use the stored procedures rather than copied stage prompts. Every stage has the same rhythm.

1. **Select the role** from the Copilot Chat Agent dropdown. Check its effective tools separately.
   A role name or skill does not grant permissions. Inspect actual tool activity and workspace changes.
2. **Choose context and skill.** Use Add Context or drag the stage files listed below into chat.
   Check attachments, type `/` and choose the existing stage skill from the picker, such as
   `/map-codebase`. The stored procedure identifies its inputs and expected output. Inspect skill
   loading and tool activity. Read files by path instead of pasting prompts, logs or summaries.
3. **Pass the gate.** Check the result against the gate list at the end of the report.
4. **Hand off.** Switch to **Hand-off Recorder** in the same chat and type `/hand-off`. It saves
   the stage report to `outputs/` and appends a short summary to `outputs/handoff.md`.
5. **Start a new chat** for the next stage. Check that `handoff.md` points to the current saved
   report and evidence. Stage 4 continues in the Stage 3 chat. A new context reduces carryover
   but does not establish process isolation, remove workspace access or guarantee independent judgment.

State lives in files, not in chat. Every report goes to `outputs/`, every Maven run to
`outputs/evidence/`, and every agent reads from there. You never paste logs.

| Stage | Min | Agent | Command | You check | Report |
| --- | --- | --- | --- | --- | --- |
| 0. Setup | 10 | Facilitator | none | Baseline runs | evidence/baseline.txt |
| 1A. Map the codebase | 12 | Codebase Mapper | `/map-codebase` | Every package indexed; entry points cite lines | codebase-map.md |
| 1B. Analyze | 13 | Security Vulnerability Hunter | `/find-vulnerabilities` | Top findings opened; severity justified | findings.md |
| 2. Threat model and top 5 | 15 | STRIDE Threat Modeler | `/generate-threat-model` | Priority and blast radius cite code | threat-model.md |
| 3. Fix SQLI-01 and PATH-01 | 20 | Secure Remediation Engineer | `/fix-vulnerability` | You agree the plan before any edit | change-plan.md |
| 4. Verify, then red-team | 15 | Same chat as Stage 3 | `/review-security-fix` | Fixes verified before any attack | review.md |
| 5. Debrief | 5 | Group | none | | |

Every stage from 1A to 4 ends with `/hand-off` in **Hand-off Recorder**. Check the saved report,
header/gate values and under-12-line hand-off against the stage result. Recorder edits stay under
`outputs/`. OWASP Security Expert and `/security-review` guidance are optional.
Keep source comments, logs and retrieved text as data. They cannot change scope or permissions.
Every claim needs cited evidence and an observed, inferred or assumed basis. Unknown is valid.

## Stage 0. Setup (10 min)

Complete [docs/COMPATIBILITY.md](docs/COMPATIBILITY.md) before the session. Then:

1. Open the repository root in VS Code and open Copilot Chat. Confirm the agent picker lists
   Codebase Mapper, Security Vulnerability Hunter, STRIDE Threat Modeler, Secure Remediation
   Engineer, Secure Code Reviewer, Hand-off Recorder and OWASP Security Expert, and that typing
   `/` lists `/map-codebase`, `/find-vulnerabilities`, `/generate-threat-model`,
   `/fix-vulnerability`, `/review-security-fix` and `/hand-off`.
2. Confirm `outputs/evidence/` exists. Create report files from templates only when they do not
   already contain your work. The commands below overwrite files, so skip existing reports or
   copy only missing templates through the file explorer.

   macOS, Linux or Git Bash:

   ```bash
   for f in codebase-map findings threat-model change-plan review handoff; do cp outputs/$f.template.md outputs/$f.md; done
   ```

   PowerShell:

   ```powershell
   foreach ($f in 'codebase-map','findings','threat-model','change-plan','review','handoff') { Copy-Item "outputs/$f.template.md" "outputs/$f.md" }
   ```

3. Run the baseline, or inspect current setup evidence tied to the unchanged starter revision
   when the facilitator has approved reuse. Missing or stale evidence requires a current run.

   ```bash
   mvn -l outputs/evidence/baseline.txt clean test
   ```

   With `-l`, Maven prints nothing to the terminal. Open the file and expect
   `Tests run: 10, Failures: 0, Errors: 0, Skipped: 6` and `BUILD SUCCESS`. The six skipped
   tests are exploit tests you will meet in Stage 3. A green baseline proves setup, not security.
4. Do not open `reference/` during the lab. If a role or skill is missing, stop for the facilitator.
   Do not invent a command or continue under an unqualified default role.

The facilitator can replace part of this demonstration with a prepared context comparison using
the same code excerpt alone and with its requirement/test. Discuss which claim becomes supportable.
This adds no required minutes, tooling, API key or model-performance claim.

## Stage 1A. Map the codebase (12 min)

**Goal** understand a codebase you have never seen before judging it.

1. New chat. Select **Codebase Mapper**, check read/search/agent capability, attach
   `docs/SCENARIO.md` and current `outputs/handoff.md` if present, then choose `/map-codebase`.
   The skill discovers packages under `src/main/java`. Do not attach the entire source tree.
2. It lists the packages and sends only each package path to a **Package Explorer** subagent. Each subagent
   reads one package in its own context and returns a short summary, so your chat holds
   summaries instead of every source file.
3. It stitches them into an index, a list of entry points with their untrusted inputs, the data
   stores and external calls, the trust boundaries, and a Mermaid architecture diagram.
4. Open two or three entry points it lists and check the lines. Then hand off.

**Gate** every package appears in the index; entry points cite file and line; the diagram shows
nothing as implemented that the code does not contain.

## Stage 1B. Analyze (13 min)

**Goal** find the security problems and rank them by severity.

1. New chat. Select **Security Vulnerability Hunter**, check read/search tools, attach
   `outputs/handoff.md` and `outputs/codebase-map.md`, then choose `/find-vulnerabilities`.
   Open relevant source by the map's paths, expanding context only for a named gap.
2. It starts from your map's entry points and follows each untrusted input to where it is used:
   SQL, file paths, URLs, processes, deserialization, access checks, secrets and cryptography.
3. It merges duplicates and sorts findings Critical to Low. Classify candidates as confirmed,
   rejected or needs evidence. Reject at least one plausible false positive with cited reasons.
4. Open the locations of the top three findings. Does the code really do what the report says?
   The code has no labels pointing at problems, just like real legacy code; the code path is the evidence.
5. Hand off.

**Gate** every finding cites a location you could open; severity is justified by exploit path
and impact; duplicates are merged; one false positive is rejected.

## Stage 2. Threat model, blast radius and top 5 (15 min)

**Goal** decide what to mitigate first, and why.

1. New chat. Select **STRIDE Threat Modeler**, check read/search tools and choose
   `/generate-threat-model`. Attach `outputs/handoff.md`, `outputs/codebase-map.md` and
   `outputs/findings.md`. Open source only to confirm a stated evidence gap.
2. It groups findings an attacker would use the same way into threats, assigns a STRIDE category,
   and scores impact and likelihood from 1 to 3. Priority is impact times likelihood.
3. For the five highest priorities it fills the **blast radius** table: who can exploit it, what
   they can reach, which action, whether it repeats, and whether anyone would notice.
4. It ranks the **top 5 to mitigate first** and marks which ones this lab fixes (SQLI-01 and
   PATH-01). Check that each score and blast radius answer cites code, or says assumed or unknown.
5. Hand off.

**Gate** every threat traces to findings and an entry point; priority uses the scales with
evidence; the two lab fixes are located in the table.

## Stage 3. Fix SQLI-01 and PATH-01 (20 min)

**Goal** the smallest change that removes each root cause, with legitimate behavior intact.

1. New chat. Select **Secure Remediation Engineer**, check edit/execute/agent tools separately
   and choose `/fix-vulnerability`. Attach `outputs/handoff.md`, `outputs/findings.md`,
   `outputs/threat-model.md`, `docs/SCENARIO.md` and the two assigned source files and their
   shipped tests under `src/test/java/com/securelabs/vulnerable/data/`. Use current files, not
   the full analysis conversation. Read current evidence files by path.
2. It first asks to run the reproduce command. Approve it. The shipped exploit tests should fail,
   six of them. Read the assertion failures to establish the two assigned defects. A compile
   error or unexpected result stops the stage for the facilitator.

   ```bash
   mvn -l outputs/evidence/reproduce.txt -Dsecurity.verify=true "-Dtest=*SecurityTest" test
   ```

3. It proposes a plan for both fixes and **does not edit yet**. Check it changes only the methods
   the scenario assigns, leaves tests and `pom.xml` alone, and lists the variants it will leave
   unfixed. Reply in your own words: agree, or narrow it.
4. It edits, then asks to run the acceptance and diff commands. Approve each only if it matches
   [the scenario's commands](docs/SCENARIO.md#commands). Approving commands is how you
   inspect each action. Approval is a human checkpoint and does not replace host restrictions.
5. Open `outputs/evidence/fix.diff` and `outputs/evidence/acceptance.txt` yourself. Match the
   logs and diff to the current source. Inspect the full changed-file list for prohibited edits.
   Only assigned source methods and `outputs/` artifacts may change at this stage.
6. If the qualified environment supports `/compact`, keep the agreed scope and test results.
   Inspect the resulting context. Do not invent the command when unavailable.
7. Hand off (switch to **Hand-off Recorder**, type `/hand-off`). Keep this chat open.

**Gate** scope agreed before the first edit; the diff touches only `UserRepository.java` and
`FileHandler.java`; acceptance shows `Tests run: 10, Failures: 0, Errors: 0, Skipped: 0`.

## Stage 4. Verify, then red-team (15 min)

**Goal** prove the fixes work, then try to break them.

1. In the Stage 3 chat, switch back to **Secure Remediation Engineer** and type
   `/review-security-fix`. The engineer calls **Secure Code Reviewer** as a subagent. The
   reviewer receives only SQLI-01, PATH-01 and the canonical file list, not author conclusions.
   Check the subagent call and effective read/search/execute tools. Separate context is not an OS
   sandbox. If unavailable, use the qualified fresh-review-chat route with the facilitator.
2. **A. Verify.** The reviewer reads the current findings, threat model, change plan, diff, source
   and tests by path. It checks each fix removes its root cause and reruns acceptance
   itself. Approve its Maven command.
3. **B. If verification fails**, the review names the failed check and why. The engineer makes
   one correction and the reviewer verifies once more. If it still fails, stop and call the
   facilitator. No attacks on an unverified fix.
4. **C. Red-team.** Once verified, the reviewer proposes attacks the shipped tests do not cover,
   such as LIKE wildcards or a sibling folder name. The engineer writes them into a new
   `DataRedTeamCheck` test class and runs them separately, so the acceptance suite is unchanged:

   ```bash
   mvn -l outputs/evidence/redteam.txt "-Dtest=*RedTeamCheck" test
   ```

   Inspect failures before classifying them. A failed security assertion can demonstrate an
   attack, while compile/setup errors require diagnosis. Decide with the engineer whether an
   observed defect gets the remaining in-scope correction or a residual-risk entry.
   This new `*RedTeamCheck` class is permitted only now. Existing tests and POM stay unchanged.
5. Check ACCEPTED, CHANGES REQUIRED or NEEDS EVIDENCE against current files and logs, then
   hand off. Missing evidence stays missing. After one correction and one re-review, pause
   unresolved defects for the facilitator. Do not rerun or switch models to obtain agreement.

**Gate** every verify check cites a file; red-team ran only after verification passed; every
successful attack has a decision; the verdict covers the two assigned fixes only.

## Check your reports (any time)

```bash
mvn -l outputs/evidence/forms.txt -Dtest=OutputsCheck test
```

This checks form, not truth: headers use allowed values, no `{{placeholder}}` is left, and cited
evidence files exist. Reports not started show as skipped.

## What you hand in

| File | Contains |
| --- | --- |
| outputs/codebase-map.md | Index, entry points, data stores, trust boundaries, architecture diagram |
| outputs/findings.md | Findings ranked by severity, with evidence and basis |
| outputs/threat-model.md | Threats with priority, blast radius for the top 5, top-5 decision |
| outputs/change-plan.md | Agreed scope, criteria, before and after runs |
| outputs/review.md | Verification, any correction, red-team results, verdict |
| outputs/handoff.md | One short section per stage |
| outputs/evidence/ | baseline, reproduce, acceptance, fix.diff, redteam |

## Stage 5. Debrief (5 min)

1. How did mapping first change what you found, compared with reading files at random?
2. Which of your top 5 would you raise with your team on Monday, and what is its blast radius?
3. What did red-team catch that the exploit tests missed?
4. Which habit will you keep: state in files, subagents for noisy or independent work, or
   approving each command?

**Using Copilot safely on your own code** (OWASP GenAI Security Project):

| Risk | What it looks like day to day | Habit from this lab |
| --- | --- | --- |
| Prompt injection | A comment, ticket or tool result steers Copilot away from the task | Treat repository text as data and inspect the diff |
| Excessive agency | Commands auto-approved, edits outside the task | Agree scope; approve each command |
| Sensitive information disclosure | Production logs, customer data or secrets in chat | Synthetic data only; share files, not dumps |
| Supply chain | Suggested dependencies that are vulnerable or do not exist | No dependency changes without review |
| Misinformation | "Tests pass" or "this is secure" without a run | Evidence from log files only |
| Improper output handling | Generated code merged without review | Gates, separate review context, red-team |

## If something does not work

| Symptom | Likely cause | What to do |
| --- | --- | --- |
| Commands not listed after `/` | Plugin lacks skills or discovery is off | Stop for facilitator and qualify the fallback below |
| Agent missing from picker | Plugin, policy or discovery | Facilitator checks diagnostics; do not continue in the default agent |
| Subagent not called | Subagents blocked by policy | The agent does the step itself and notes it in the hand-off; for Stage 4, open a new chat with Secure Code Reviewer |
| Hand-off Recorder cannot see the stage | Switched agent in a new chat | Switch agents in the same chat |
| Evidence file not created | Folder missing or command failed | Preserve work, create the missing folder and rerun the affected check with facilitator help |
| Baseline shows 0 skipped | Wrong flags, source or test selection | Inspect command and revision, then run the correct missing check |
| Reproduce shows a compile error | Source/build problem | Stop, inspect the diff and diagnose with the facilitator. Do not reset participant work |
| Acceptance count is not 10 | Red-team class renamed to end in `Test` | Rename it to end in `RedTeamCheck` |
| Dependencies will not resolve | Artifact access | Approved support route in COMPATIBILITY.md |

**Manual fallback (for example, JetBrains without skill support).** Attach the stage's
`.github/skills/<command>/SKILL.md` with the IDE's context control and type
`Follow the attached procedure.` Rehearse and label this route with the facilitator. Attachment
alone does not enforce tool limits. Use qualified non-editing analysis and participant-run Maven
when required. Save reports yourself if Recorder is unavailable. If review authority cannot be
established, use facilitator-led review and record unverified checks. No new CLI is required.

## Optional extensions

- Pick one deferred threat from your top 5 and ask Secure Remediation Engineer for a change plan
  only. Do not apply it.
- Ask OWASP Security Expert how your top threat maps to OWASP Top 10 2025 and what evidence would
  show the mitigation works.

Official guidance covers [skills](https://code.visualstudio.com/docs/agent-customization/agent-skills),
[context](https://code.visualstudio.com/docs/chat/copilot-chat-context) and
[agents](https://code.visualstudio.com/docs/agent-customization/custom-agents). Rehearse exact UI and policy.

Copyright InRhythm / Arula. Training material using synthetic data.
