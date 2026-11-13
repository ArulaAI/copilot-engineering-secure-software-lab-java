# Secure Software Development with GitHub Copilot

A 90-minute Java lab about inheriting an unfamiliar service, mapping its attack surface, investigating
findings and using STRIDE and blast radius to prioritize five threats. Create and critique selected
fixes sequentially, then review the cumulative changes against the original findings.

Start with [compatibility](docs/COMPATIBILITY.md), [the scenario](docs/SCENARIO.md) and
[the Lab Action Guide](LAB_ACTION_GUIDE.md). The facilitator decides how many priorities to implement
live, usually one or two. All selected tasks remain available for continuation. No agent is restricted
to a specific issue, file pair or implementation count.

## Prerequisites

- Java 17 or later and Maven 3.9 or later with the organization's existing settings.xml.
- Approved Copilot access in VS Code, or the rehearsed IntelliJ fallback.
- Repository access and pre-session build/customization checks.

No new dependency, scanner, database service, CLI, MCP server or external API is required. Spring
application startup is outside the required route. A passing baseline includes explicitly skipped
seeded security checks and does not prove security. See [verification](docs/VERIFICATION.md) for the
current fixture counts; selected tasks define their own acceptance checks.

## Schedule

| Stage | Minutes | Output |
| --- | --- | --- |
| 0 Setup and demonstration | 10 | Baseline and customization readiness |
| 1A Map | 12 | Source-grounded architecture and attack surface |
| 1B Investigate | 13 | Supported findings and uncertainty |
| 2 Threat model and priorities | 15 | STRIDE, blast radius and top five |
| 3 Create and critique | 20 | As many independently verified tasks as time permits |
| 4 Final review | 15 | Cumulative verdict and remaining risks |
| 5 Debrief | 5 | Transfer to participants' work |
| Total | 90 | |

Each stage closes with a separate /hand-off that records the current state; it never blocks. Review and debrief are never cut; the facilitator narrows
live implementation scope rather than reporting incomplete work as passed. Five completed fixes
within this schedule are not promised.

## Repository

- src/main/java/ contains the synthetic vulnerable service and helpers.
- src/test/java/ contains behavior, opt-in exploit and workflow-form checks.
- .github/ contains eight profiles (seven visible and one package subagent) and seven explicit skills.
- outputs/ contains templates, the task ledger, per-task critiques and handoff format.
- docs/ contains the scenario, security references and [facilitator guide](docs/INSTRUCTOR_GUIDE.md).

Participant-generated architecture and findings belong in outputs/. No completed answer key or
facilitator solution is included in the working tree. Older Git history can still contain earlier
material; a clean participant distribution must omit prior Git objects when separation is required.

Facilitators use [the run sheet](docs/RUN_SHEET.md), [workflow architecture](docs/WORKFLOW_ARCHITECTURE.md),
[verification](docs/VERIFICATION.md) and [rehearsal worksheet](docs/WORKFLOW_REHEARSAL.md).
Native IDE behavior and session timing require target-environment rehearsal.

Copyright InRhythm / Arula. Training material using synthetic data.
