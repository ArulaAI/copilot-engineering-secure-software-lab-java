# Secure Software Development with GitHub Copilot

A 90-minute hands-on learning lab. Assess a Java security finding, model its impact,
implement a bounded fix, and validate the result with application regression checks.

## Start here

1. Complete [setup and compatibility checks](docs/COMPATIBILITY.md).
2. Open [the participant action guide](LAB_ACTION_GUIDE.md).
3. Read [the refund scenario](docs/SCENARIO.md).
4. Run `mvn clean test` at the repository root.

The baseline passing does not mean the application is secure. Two security acceptance
checks are deliberately skipped until enabled. Run
`mvn -Dlab.verify=true -Dtest=RefundSecurityTest test` before the fix. Exactly two
assertion failures are expected on the starter. After the fix,
`mvn -Dlab.verify=true clean test` must pass without skipped checks.

## Prerequisites

- An approved JDK, Java 17 or later, with Maven using the same JDK.
- Maven 3.9 or later with the organization's existing approved settings.xml configuration.
- Existing approved GitHub Copilot access in VS Code or IntelliJ IDEA.
- Repository access and successful pre-session compile/test and customization checks.

No new package, Python runtime, CLI, hooks, database, or external API is required.
The POM dependency and plugin versions are unchanged from the validated lab.
Fresh Maven caches still need the organization's approved artifact repository. An offline
run is possible only after its existing artifacts are available.

Spring Boot startup is outside the required workflow. The historical dependency fixture
can prevent startup. Do not install blocked artifacts or change repository mirrors to bypass
organizational restrictions. Application-method checks do not establish HTTP/filter security.

## Session schedule

| Stage | Minutes | Outcome |
| --- | --- | --- |
| Introduction and workflow demonstration | 20 | Understand evidence, roles, and tool limits |
| Investigate the refund finding | 15 | Source-backed finding and failed acceptance checks |
| Model and prioritize the threats | 10 | Java flow and a separate AI-workflow risk |
| Plan and implement the fix | 25 | Bounded implementation change |
| Verify and independently review | 15 | Acceptance evidence and qualified verdict |
| Debrief and transfer | 5 | A reusable review procedure |
| Total | 90 | |

Laptop setup occurs before the session. Facilitators can run a parallel help room.
The flexible portion is additional discovery breadth, not verification or the debrief.

## Course structure

- `src/main/java/com/securelabs/lab/` contains the required refund starter and webhook verifier.
- `src/test/java/com/securelabs/lab/` contains real baseline and acceptance checks.
- `src/main/java/com/securelabs/vulnerable/` is an intentionally vulnerable historical review corpus.
- `.github/` contains five custom agents, four Local prompt adapters, four phase skills, one workflow overview skill,
  repository instructions, and scoped Java instructions.
- `outputs/` contains five participant evidence templates, including an acceptance matrix.
- `docs/` contains the scenario, compatibility steps, and facilitator delivery material.
- `reference/` contains facilitator solutions and an explicitly unsafe historical archive,
  outside compilation. Do not consult it during participant investigation or remediation.

The public clone exposes facilitator files. Instructions are an educational boundary,
not a technical access restriction. For independent assessment distribute a participant
package that omits reference/ and facilitator-only docs, rather than relying on prompt rules.

## Facilitators

Use [INSTRUCTOR_GUIDE](docs/INSTRUCTOR_GUIDE.md), [RUN_SHEET](docs/RUN_SHEET.md),
[VERIFICATION](docs/VERIFICATION.md), and [change coverage](docs/REMEDIATION_STATUS.md).
This branch requires a rehearsal on the target Fidelity IDE/plugin/harness/model combination.
No minimum plugin version is claimed until the actual installation is recorded.
Threat modeling combines the Java view with a small AI-assisted workflow view using the
[AI and agentic security reference](docs/owasp-reference/ai-security.md). It uses Agentic
Applications 2026 and LLM Applications 2026 alongside Web Top 10 2025. The read-only synthetic
challenge can be a paper discussion, adding no service or dependency requirement.
The primary proposed route is explicit phase skills in Copilot Chat, with separate agent
selection. Local prompt adapters and the rehearsed manual route provide compatibility.
See [workflow architecture](docs/WORKFLOW_ARCHITECTURE.md) and the facilitator-only
[rehearsal worksheet](docs/WORKFLOW_REHEARSAL.md). Speed, usage, and quality improvements
are expected hypotheses until measured. Native skill support may require an approved plugin
update if the existing installation lacks it.

Copyright InRhythm / Arula. Training material using synthetic data.
