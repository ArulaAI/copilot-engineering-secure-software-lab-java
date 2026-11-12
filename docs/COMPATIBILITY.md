# Setup and compatibility

Complete these checks before joining the 90-minute course. Use the existing approved
installation. No plugin version floor is asserted until it is verified on the target machines.

## Java and Maven

1. Clone the course release branch supplied by the facilitator and open its repository root.
2. Run `java -version` and `mvn -version`. Java must be 17 or later, Maven 3.9 or later,
   with Maven selecting the intended JDK.
3. Keep the organization's approved settings.xml. Obtain internal instructions from the local
   coordinator. Validate access for this business unit rather than reusing another unit's link.
4. Run `mvn clean test`. Expect BUILD SUCCESS, nine discovered checks, seven passing and
   two skipped optional acceptance checks. This establishes setup, not security acceptance.
5. If dependencies cannot resolve, use the approved artifact-support route. Do not install
   blocked artifacts, change mirrors, or bundle restricted packages.

With a populated Maven cache, `mvn -o clean test` checks local execution without artifact
network access. A fresh cache still requires approved repository access. Copilot itself
requires its existing approved service connectivity.

## VS Code

Record the actual VS Code version, Copilot extension version, selected harness, and model.
In the validated Local route, confirm all five agents in the picker and all four slash
prompts. Select Security Vulnerability Hunter and run /find-vulnerabilities with the scenario
and source attached. Reading roles return chat text. Save outputs/findings.md yourself.

The Copilot harness does not use Local prompt files the same way. Verify discovery of the
security-review skill and the selected custom agent. If skill invocation is unavailable,
open .github/skills/security-review/SKILL.md and explicitly request its procedure. Do not
claim native skill loading from that manual fallback. Match agent tools to the selected harness
using editor diagnostics and observed activity. Do not assume names have identical semantics.

## IntelliJ IDEA

Record IDEA and Copilot plugin versions, available agent mode/customizations, and model.
Use the plugin's context-attachment controls to select the scenario, source, and tests.
Check actual attachments before submitting. Do not rely on VS Code #file or @workspace syntax.
If custom agents or skills are unavailable, open the relevant agent file and skill as explicit
instructions in chat. This fallback does not enforce tool restrictions. Keep analysis/review
in a non-editing mode, decline proposed edits, and save reports manually. Run Maven yourself.
Confirm each fallback is approved and rehearsed. If an analysis-only mode cannot be established,
use the facilitator-led review instead of claiming the read-only role is enforced.

## Model and result expectations

Use the model approved for the session and available to all participants. No model is pinned
in repository agents. Choose from the existing organization's allowed models. Record the exact
choice. Do not request paid upgrades or guarantee identical findings from different models.

## Harness lifecycle

The documentation reviewed for this release lists several JetBrains customizations as preview.
VS Code documents Local prompt files separately from Copilot-harness skills and lists Local
removal in February 2027. Recheck before future delivery. A tested Local route can serve this
session, while the skill/manual procedure supplies a separately qualified route.

- [GitHub feature matrix](https://docs.github.com/en/copilot/reference/copilot-feature-matrix)
- [GitHub customization reference](https://docs.github.com/en/copilot/reference/customization-cheat-sheet)
- [VS Code prompt files](https://code.visualstudio.com/docs/agent-customization/prompt-files)
- [VS Code agent configuration](https://code.visualstudio.com/docs/agents/reference/custom-agent-file-reference)

## Facilitator environment record

Complete one row per supported delivery path before declaring it ready.

| Machine/group | Release SHA | JDK / Maven | IDE / plugin | Harness / model | Agent/skill discovery | Prompt/context/output test | Baseline + fixed acceptance | Status |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Fidelity VS Code | Record | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |
| Fidelity IntelliJ | Record | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |

Historical dry run established compile/test and Copilot analysis on selected machines. It did
not establish full execution of this revised workflow or Spring Boot startup. Rehearse the
exact release. No external scanner, Python, Copilot CLI, hook, or MCP server is required.
