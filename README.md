# Secure Software Development with GitHub Copilot

A 90-minute hands-on lab for Java engineers. You inherit an unfamiliar legacy service, map it
with Copilot, find and rank its security problems, threat-model them to choose the top 5 to
mitigate, fix two of them with test evidence, and red-team your own fixes.

## Start here

1. Complete [setup and compatibility checks](docs/COMPATIBILITY.md).
2. Read [the scenario](docs/SCENARIO.md): the codebase, the two assigned fixes and the commands.
3. Follow [the participant action guide](LAB_ACTION_GUIDE.md).

The baseline `mvn -l outputs/evidence/baseline.txt clean test` passes with six exploit tests
skipped. Passing does not mean the code is secure; the exploit tests are switched on in Stage 3.

## Prerequisites

- An approved JDK, Java 17 or later, with Maven using the same JDK.
- Maven 3.9 or later with the organization's approved settings.xml.
- Approved GitHub Copilot access in VS Code (IntelliJ IDEA via the manual fallback).
- Repository access and a successful pre-session baseline and customization check.

No new package, Python runtime, CLI, hook, database server or external API is required. The
exploit tests use the in-memory H2 database already in the POM. Spring Boot startup is outside
the lab.

## Session schedule

| Stage | Minutes | Outcome |
| --- | --- | --- |
| 0. Setup | 10 | Baseline runs; agents and commands visible |
| 1A. Map the codebase | 12 | Index, entry points, architecture diagram |
| 1B. Analyze | 13 | Findings ranked by severity |
| 2. Threat model and top 5 | 15 | Prioritized threats, blast radius, top 5 to mitigate |
| 3. Fix SQLI-01 and PATH-01 | 20 | Minimal fixes proven by shipped exploit tests |
| 4. Verify, then red-team | 15 | Isolated review, attack tests, verdict |
| 5. Debrief | 5 | Habits to carry back |
| Total | 90 | |

## Repository layout

- `src/main/java/com/securelabs/vulnerable/` is the legacy codebase under study.
- `src/test/java/com/securelabs/vulnerable/data/` holds the shipped behavior and exploit tests.
- `.github/` holds eight custom agents (one is a hidden subagent), the stage skills, `/hand-off`,
  repository instructions and scoped Java instructions.
- `outputs/` holds the report templates (YAML header, evidence tables, gate list);
  `outputs/evidence/` holds run logs.
- `docs/` holds the scenario, compatibility steps and facilitator material.
- `reference/` holds facilitator solutions, the finding catalog and an unsafe historical archive,
  outside compilation. Do not consult it during the lab.

The public clone exposes facilitator files; this is an educational boundary, not an access
control. For assessment, distribute a package without `reference/` and facilitator docs.

## Facilitators

Use [INSTRUCTOR_GUIDE](docs/INSTRUCTOR_GUIDE.md), [RUN_SHEET](docs/RUN_SHEET.md),
[WORKFLOW_ARCHITECTURE](docs/WORKFLOW_ARCHITECTURE.md), [VERIFICATION](docs/VERIFICATION.md)
and the [rehearsal worksheet](docs/WORKFLOW_REHEARSAL.md). Rehearse on the target IDE, plugin,
harness and model before delivery; subagents, agent switching and tool limits must be confirmed
there.

Copyright InRhythm / Arula. Training material using synthetic data.
