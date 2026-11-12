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
The primary route to qualify is Copilot Chat with the Copilot harness. Confirm all five agents
and all five skills. Select the intended agent first, verify effective tools, then invoke the
corresponding lab-* skill. Check chat.useAgentSkills and actual skill discovery. The documented
github.copilot.chat.cli.customAgents.enabled setting may need enabling in supported versions
and defaults to false in the current reference. Use only settings allowed by the organization.
These are environment qualification checks, not automatic configuration changes.

Skills do not preserve a prompt's agent/model/tools fields. Confirm the implementation role
can execute approved commands and that reading roles cannot edit or execute. File-scope rules
remain instructions, not an access-control guarantee. Inspect actual activity and diffs.
All course skills use explicit invocation and inline context. No forked skill, experimental
skill-tool setting, Copilot CLI, or additional runtime is required.

On the qualified Local route, the four old slash names are thin adapters selecting agents
and reading the canonical phase skill procedure. The native phase skills use distinct lab-*
names to avoid ambiguous commands when both formats appear. The adapters do not establish
native skill invocation. Compare behavior before retiring a compatibility entrypoint.

If native invocation is unavailable, stop for the facilitator. Use a rehearsed walkthrough of
the existing phase SKILL.md and shared contract, with participant-run checks and report saving.
Do not invent a slash command or substitute a long copied operating prompt. Label this route
accurately and match effective tools to the harness using diagnostics and observed activity.

## IntelliJ IDEA

Record IDEA and Copilot plugin versions, available agent mode/customizations, and model.
Use the plugin's context-attachment controls to select the scenario, source, and tests.
Check actual attachments before submitting. Do not rely on VS Code #file or @workspace syntax.
If custom agents or skills are unavailable, stop for the facilitator's rehearsed walkthrough of
the existing agent, phase skill and contract. Keep analysis/review in a qualified non-editing
mode, decline proposed edits, save reports manually and run Maven yourself. No long replacement
chat script is required. Confirm this route is approved and rehearsed. If an analysis-only mode
cannot be established, use facilitator-led or paper review and record the enforcement limit.

## Model and result expectations

Use the model approved for the session and available to all participants. No model is pinned
in repository agents. Choose from the existing organization's allowed models. Record the exact
choice. Do not request paid upgrades or guarantee identical findings from different models.

## Harness lifecycle

JetBrains announced its Copilot harness as generally available in August 2026 and default
when available in October 2026. Current GitHub documentation still lists individual JetBrains
customizations as preview. Harness availability does not establish feature availability.
Qualify each feature in the actual approved plugin and tenant. Native skills may require an
approved update if the existing version lacks them; retain the qualified fallback for delivery.
VS Code documents Local prompt files separately from Copilot-harness skills and lists Local
removal in February 2027. Recheck before future delivery.

- [GitHub feature matrix](https://docs.github.com/en/copilot/reference/copilot-feature-matrix)
- [GitHub customization reference](https://docs.github.com/en/copilot/reference/customization-cheat-sheet)
- [VS Code skill support](https://code.visualstudio.com/docs/agent-customization/agent-skills)
- [VS Code migration](https://code.visualstudio.com/docs/agent-customization/migrate-customizations)
- [VS Code settings](https://code.visualstudio.com/docs/agents/reference/ai-settings)
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
