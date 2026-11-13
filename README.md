# Secure Software Development with GitHub Copilot

A 60-minute Java lab about inheriting an unfamiliar service, mapping its attack surface, investigating
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
| 0 Setup and demonstration | 5 | Baseline and customization readiness |
| 1A Map | 5 | Source-grounded architecture and attack surface |
| 1B Investigate | 8 | Supported findings and uncertainty |
| 2 Threat model and priorities | 10 | STRIDE, blast radius and top five |
| 3 Create and critique | 22 | Named fix, focused tests and independent review |
| 4 Final review | 7 | Actual changes and remaining risk |
| 5 Debrief | 3 | Transfer to participants' work |
| Total | 60 | |

Handoff is optional: `/handoff` records current state whenever needed. No stage requires approval fields or transition sign-off. Review and debrief are never cut; the facilitator narrows
live implementation scope rather than reporting incomplete work as passed. Five completed fixes
within this schedule are not promised. Stage 3 has no mandatory plan, ledger, cycle counter or artifact validator.

## Repository

- src/main/java/ contains the synthetic vulnerable service and helpers.
- src/test/java/ contains behavior, opt-in exploit and workflow-form checks.
- .github/ contains seven visible profiles, seven skills and seven thin Local prompt entry points.
- outputs/ is reserved for generated reports. Each skill bundles its relevant templates.
- docs/ contains the scenario, security references and [facilitator guide](docs/INSTRUCTOR_GUIDE.md).

Participant-generated architecture and findings belong in outputs/. No completed answer key or
facilitator solution is included in the working tree. Older Git history can still contain earlier
material; a clean participant distribution must omit prior Git objects when separation is required.

Facilitators use [the run sheet](docs/RUN_SHEET.md), [workflow architecture](docs/WORKFLOW_ARCHITECTURE.md),
[verification](docs/VERIFICATION.md) and [rehearsal worksheet](docs/WORKFLOW_REHEARSAL.md).
Native IDE behavior and session timing require target-environment rehearsal.

Copyright InRhythm / Arula. Training material using synthetic data.

Final deliverable: [structured security-review template](.github/skills/review-security-fix/templates/security-review.template.md),
used by Stage 4 to generate outputs/security-review.md.

VS Code Local entry commands: `/map`, `/investigate`, `/threat-model`, `/create-fix`,
`/critique-fix` (or `/critique-fix final`), `/handoff` and optional `/lab-workflow`.
Each selects its agent automatically. See [routing and formats](docs/WORKFLOW_ARCHITECTURE.md).
