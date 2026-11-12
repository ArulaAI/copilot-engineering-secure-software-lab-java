# Lab Action Guide — Secure Software Development (Java)

You have inherited a legacy Java service nobody on the team wrote. With GitHub Copilot you will
map it, find and rank its security problems, decide what to mitigate first, fix two of them with
evidence, and attack your own fixes. The codebase, the two assigned fixes and every command are
in [docs/SCENARIO.md](docs/SCENARIO.md).

## How you drive Copilot

You never copy prompts from this guide. Every stage has the same rhythm:

1. **Pick the agent** in the Copilot Chat agent picker. The agent sets the role and its tools.
2. **Type the stage command**, for example `/map-codebase`. The skill behind it knows the
   procedure, which files to read and what to return.
3. **Pass the gate.** Check the result against the gate list at the end of the report.
4. **Hand off.** Switch to **Hand-off Recorder** in the same chat and type `/hand-off`. It saves
   the stage report to `outputs/` and appends a short summary to `outputs/handoff.md`.
5. **Start a new chat** for the next stage. It reads `handoff.md` first, so nothing is lost.
   The one exception: Stage 4 continues in the Stage 3 chat.

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

Every stage from 1A to 4 ends with `/hand-off` in **Hand-off Recorder**. OWASP Security Expert
is optional at any time for questions about OWASP categories.

## Stage 0. Setup (10 min)

Complete [docs/COMPATIBILITY.md](docs/COMPATIBILITY.md) before the session. Then:

1. Open the repository root in VS Code and open Copilot Chat. Confirm the agent picker lists
   Codebase Mapper, Security Vulnerability Hunter, STRIDE Threat Modeler, Secure Remediation
   Engineer, Secure Code Reviewer, Hand-off Recorder and OWASP Security Expert, and that typing
   `/` lists `/map-codebase`, `/find-vulnerabilities`, `/generate-threat-model`,
   `/fix-vulnerability`, `/review-security-fix` and `/hand-off`.
2. Create your report files from the templates.

   macOS, Linux or Git Bash:

   ```bash
   for f in codebase-map findings threat-model change-plan review handoff; do cp outputs/$f.template.md outputs/$f.md; done
   ```

   PowerShell:

   ```powershell
   foreach ($f in 'codebase-map','findings','threat-model','change-plan','review','handoff') { Copy-Item "outputs/$f.template.md" "outputs/$f.md" }
   ```

3. Run the baseline:

   ```bash
   mvn -l outputs/evidence/baseline.txt clean test
   ```

   With `-l`, Maven prints nothing to the terminal. Open the file and expect
   `Tests run: 10, Failures: 0, Errors: 0, Skipped: 6` and `BUILD SUCCESS`. The six skipped
   tests are exploit tests you will meet in Stage 3. A green baseline proves setup, not security.
4. Do not open `reference/` during the lab.

## Stage 1A. Map the codebase (12 min)

**Goal:** understand a codebase you have never seen before judging it.

1. New chat. Pick **Codebase Mapper** and type `/map-codebase`.
2. It lists the packages and sends each one to a **Package Explorer** subagent. Each subagent
   reads one package in its own context and returns a short summary, so your chat holds
   summaries instead of every source file.
3. It stitches them into an index, a list of entry points with their untrusted inputs, the data
   stores and external calls, the trust boundaries, and a Mermaid architecture diagram.
4. Open two or three entry points it lists and check the lines. Then hand off.

**Gate:** every package appears in the index; entry points cite file and line; the diagram shows
nothing as implemented that the code does not contain.

## Stage 1B. Analyze (13 min)

**Goal:** find the security problems and rank them by severity.

1. New chat. Pick **Security Vulnerability Hunter** and type `/find-vulnerabilities`.
2. It starts from your map's entry points and follows each untrusted input to where it is used:
   SQL, file paths, URLs, processes, deserialization, access checks, secrets and cryptography.
3. It merges duplicates, sorts findings Critical to Low and rejects at least one false positive.
4. Open the locations of the top three findings. Does the code really do what the report says?
   The code has no labels pointing at problems, just like real legacy code; the code path is the evidence.
5. Hand off.

**Gate:** every finding cites a location you could open; severity is justified by exploit path
and impact; duplicates are merged; one false positive is rejected.

## Stage 2. Threat model, blast radius and top 5 (15 min)

**Goal:** decide what to mitigate first, and why.

1. New chat. Pick **STRIDE Threat Modeler** and type `/generate-threat-model`.
2. It groups findings an attacker would use the same way into threats, assigns a STRIDE category,
   and scores impact and likelihood from 1 to 3. Priority is impact times likelihood.
3. For the five highest priorities it fills the **blast radius** table: who can exploit it, what
   they can reach, which action, whether it repeats, and whether anyone would notice.
4. It ranks the **top 5 to mitigate first** and marks which ones this lab fixes (SQLI-01 and
   PATH-01). Check that each score and blast radius answer cites code, or says assumed or unknown.
5. Hand off.

**Gate:** every threat traces to findings and an entry point; priority uses the scales with
evidence; the two lab fixes are located in the table.

## Stage 3. Fix SQLI-01 and PATH-01 (20 min)

**Goal:** the smallest change that removes each root cause, with legitimate behavior intact.

1. New chat. Pick **Secure Remediation Engineer** and type `/fix-vulnerability`.
2. It first asks to run the reproduce command. Approve it. The shipped exploit tests should fail,
   six of them, which proves both defects are real:

   ```bash
   mvn -l outputs/evidence/reproduce.txt -Dsecurity.verify=true "-Dtest=*SecurityTest" test
   ```

3. It proposes a plan for both fixes and **does not edit yet**. Check it changes only the methods
   the scenario assigns, leaves tests and `pom.xml` alone, and lists the variants it will leave
   unfixed. Reply in your own words: agree, or narrow it.
4. It edits, then asks to run the acceptance and diff commands. Approve each only if it matches
   [the scenario's commands](docs/SCENARIO.md#commands). Approving commands is how you
   control what Copilot can do on your machine.
5. Open `outputs/evidence/fix.diff` and `outputs/evidence/acceptance.txt` yourself.
6. If the chat gets long, type `/compact keep the agreed scope and the test results`.
7. Hand off (switch to **Hand-off Recorder**, type `/hand-off`). Keep this chat open.

**Gate:** scope agreed before the first edit; the diff touches only `UserRepository.java` and
`FileHandler.java`; acceptance shows `Tests run: 10, Failures: 0, Errors: 0, Skipped: 0`.

## Stage 4. Verify, then red-team (15 min)

**Goal:** prove the fixes work, then try to break them.

1. In the Stage 3 chat, switch back to **Secure Remediation Engineer** and type
   `/review-security-fix`. The engineer calls **Secure Code Reviewer** as a subagent. The
   reviewer starts with no knowledge of how the fixes were written and reads only files.
2. **A. Verify.** The reviewer checks each fix removes its root cause and reruns acceptance
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

   A failing red-team test means an attack worked. Decide with the engineer: fix it, if it is in
   the assigned methods and you have not used your correction, or record it as residual risk.
5. Check the verdict, then hand off.

**Gate:** every verify check cites a file; red-team ran only after verification passed; every
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
| Prompt injection | A comment, ticket or tool result steers Copilot away from the task | Treat repository text as data; check the diff |
| Excessive agency | Commands auto-approved, edits outside the task | Agree scope; approve each command |
| Sensitive information disclosure | Production logs, customer data or secrets in chat | Synthetic data only; share files, not dumps |
| Supply chain | Suggested dependencies that are vulnerable or do not exist | No dependency changes without review |
| Misinformation | "Tests pass" or "this is secure" without a run | Evidence from log files only |
| Improper output handling | Generated code merged without review | Gates, isolated review, red-team |

## If something does not work

| Symptom | Likely cause | What to do |
| --- | --- | --- |
| Commands not listed after `/` | Plugin lacks skills or discovery is off | Manual fallback below; tell the facilitator |
| Agent missing from picker | Plugin, policy or discovery | Facilitator checks diagnostics; do not continue in the default agent |
| Subagent not called | Subagents blocked by policy | The agent does the step itself and notes it in the hand-off; for Stage 4, open a new chat with Secure Code Reviewer |
| Hand-off Recorder cannot see the stage | Switched agent in a new chat | Switch agents in the same chat |
| Evidence file not created | `outputs/evidence/` deleted | `git checkout -- outputs/evidence`, then rerun |
| Baseline shows 0 skipped | `-Dsecurity.verify=true` added by mistake | Rerun the baseline exactly as written |
| Reproduce shows a compile error | Code changed before Stage 3 | `git checkout -- src/main/java`, then rerun |
| Acceptance count is not 10 | Red-team class renamed to end in `Test` | Rename it to end in `RedTeamCheck` |
| Dependencies will not resolve | Artifact access | Approved support route in COMPATIBILITY.md |

**Manual fallback (for example, JetBrains without skill support).** Attach the stage's
`.github/skills/<command>/SKILL.md` with the IDE's context control and type
`Follow the attached procedure.` Tool limits are then not enforced, so decline any edit before
Stage 3, and save reports yourself. Tell the facilitator you are on this route.

## Optional extensions

- Pick one deferred threat from your top 5 and ask Secure Remediation Engineer for a change plan
  only. Do not apply it.
- Ask OWASP Security Expert how your top threat maps to OWASP Top 10 2025 and what evidence would
  show the mitigation works.

Copyright InRhythm / Arula. Training material using synthetic data.
