# Setup and compatibility

Use existing approved Java 17+, Maven 3.9+, settings.xml and GitHub Copilot access. No additional
runtime, package, database server, scanner, external API or CLI is introduced.

## Java and dependencies

Record java -version and mvn -version and verify Maven selects the intended JDK. Run mvn clean test
before the session; record actual test counts/skips. With populated approved cache, mvn -o clean test
checks local execution. A fresh cache still needs the existing approved artifact route. Copilot itself
uses the organization's approved service connectivity. Never bundle or bypass blocked packages.

The existing POM is unchanged. Its historical dependency pin can prevent optional Spring startup.
Required checks invoke methods/tests, not a running application. Do not infer HTTP deployment security
from method-level tests or claim dependency vulnerabilities have been removed.

## VS Code qualification

Record IDE, plugin, harness and model. Confirm eight profiles, including seven visible roles and hidden
Package Explorer; seven explicit skills include /hand-off and the optional /security-review overview.
Select role separately from skill. Confirm native skill discovery and inspect actual tools/activity.
Use only organization-permitted settings and models. No model pin, paid upgrade or CLI is required.

| Role | Intended tools | Permitted writes |
| --- | --- | --- |
| Codebase Mapper | read/search/edit/execute/agent | map report |
| Package Explorer, hidden | read/search | none |
| Vulnerability Hunter | read/search/edit/execute | findings report |
| STRIDE Threat Modeler | read/search/edit/execute | threat-model report |
| Remediation Engineer | read/search/edit/execute | agreed task code/new tests and necessary agreed test adaptations, plan, ledger, evidence |
| Code Reviewer | read/search/edit/execute | task/final review, task status, new critique tests, evidence |
| Hand-off Recorder | read/search/edit/execute | handoff only; execute read-only Git inspection only; works in any repository and never blocks |
| OWASP Expert | read/search | none |

Analysis execute is limited by instructions to read-only Git metadata/diff inspection; no builds or source execution.
File-level scope is instructional, not host enforcement. In particular an edit-capable analysis role
can technically edit more than its report. Inspect all actual changes. Execute-capable roles need
exact command approval and observed restrictions; a role name alone is not proof.

Rehearse Mapper calls, report creation, task/cycle persistence, fresh task review, sequential advancement,
separate same-chat Recorder invocation and cumulative final review. Subagent unavailability uses
sequential mapping, with timing impact recorded. New chat reduces context carryover but provides no
OS isolation or guarantee of independent judgment.

## IntelliJ and manual routes

Record plugin/IDE/harness/model and qualify each capability independently. Use native roles/skills where
supported. Otherwise attach the existing profile and phase SKILL.md through the IDE context control and
send `Follow the attached procedure.` Rehearse this route without assuming VS Code syntax or tool parity.
Save reports manually or run selected Maven commands yourself where needed. If non-editing scope or
fresh review execution cannot be established, use facilitator-led review and mark unverified checks.
No long replacement prompt, CLI installation or worker runtime is required.

The fallback attaches instructions but does not enforce them. Stop for facilitator assistance if the
required role/skill is absent; do not silently continue in an unrestricted default agent.

## Delivery record

| Group | Release/source state | JDK/Maven | IDE/plugin/harness/model | Discovery/tools | Sequential task loop + handoff | Runs and timing | Status |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Client VS Code | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |
| Client IntelliJ | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |

A prior dry run is not qualification of this revised flow. Confirm the exact release, complete timed
rehearsal, and record any missing features or fallback limits before claiming readiness or efficiency.

- [VS Code custom agents](https://code.visualstudio.com/docs/agent-customization/custom-agents)
- [VS Code skills](https://code.visualstudio.com/docs/agent-customization/agent-skills)
- [GitHub feature matrix](https://docs.github.com/en/copilot/reference/copilot-feature-matrix)
