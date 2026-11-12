# Setup and compatibility

Complete these checks before joining the 90-minute course. Use the existing approved
installation. No plugin version floor is asserted until it is verified on the target machines.

## Java and Maven

1. Clone the course release branch supplied by the facilitator and open its repository root.
2. Run `java -version` and `mvn -version`. Java must be 17 or later, Maven 3.9 or later,
   with Maven selecting the intended JDK.
3. Keep the organization's approved settings.xml. Obtain internal instructions from the local
   coordinator. Validate access for this business unit rather than reusing another unit's link.
4. Run `mvn clean test`. Expect BUILD SUCCESS, ten tests, four passing and six skipped exploit
   tests. This establishes setup, not security. The H2 test database is already a POM dependency.
5. If dependencies cannot resolve, use the approved artifact-support route. Do not install
   blocked artifacts, change mirrors, or bundle restricted packages.

With a populated Maven cache, `mvn -o clean test` checks local execution without artifact
network access. A fresh cache still requires approved repository access. Copilot itself
requires its existing approved service connectivity.

## VS Code

Record the actual VS Code version, Copilot extension version, selected harness, and model.
The primary route to qualify is Copilot Chat with the Copilot harness. Confirm seven agents in
the picker (Package Explorer is a hidden subagent) and seven skills (five stage commands,
/hand-off, /security-review). Select the intended agent
first, verify effective tools, then invoke the stage command. Check chat.useAgentSkills and actual skill discovery. The documented
github.copilot.chat.cli.customAgents.enabled setting may need enabling in supported versions
and defaults to false in the current reference. Use only settings allowed by the organization.
These are environment qualification checks, not automatic configuration changes.

Skills do not preserve a prompt's agent/model/tools fields. Confirm the implementation role
can execute approved commands and that reading roles cannot edit or execute. File-scope rules
remain instructions, not an access-control guarantee. Inspect actual activity and diffs.
All course skills use explicit invocation and inline context. No forked skill, experimental
skill-tool setting, Copilot CLI, or additional runtime is required.

The workflow depends on three platform behaviors to qualify on the target installation:

1. **Subagents.** Codebase Mapper calls Package Explorer (once per package) and Secure
   Remediation Engineer calls Secure Code Reviewer. Both list `agents:` and the `agent` tool.
   Confirm each call works and that the organization allows it. If not, the mapper reads packages
   itself and the learner opens a new chat for the review.
2. **Agent switching within a chat.** Hand-off Recorder must see the stage's conversation.
3. **Tool limits.** Secure Code Reviewer has execute but not edit; Hand-off Recorder has edit
   but not execute. Confirm both in observed activity.

`OutputsCheck` and `*RedTeamCheck` classes do not end in `Test`, so the default build never runs
them and the documented counts (10 run, 6 skipped; 10 run, 0 skipped) are unchanged.

The four phase skills use the course's original command names. The earlier prompt files with
the same names were removed so each name resolves to one entrypoint. Skills run in both the
Copilot and the deprecated Local harness; an installation without skill support uses the
one-line manual fallback below.

If native invocation is unavailable, attach the relevant phase SKILL.md and send the single
line `Follow the attached procedure.` The skill links the shared contract and
its inputs, so no longer prompt text is needed. Rehearse this route and label it accurately.
Reading-role output is saved by the participant. Match tool names to the selected harness
using diagnostics and observed activity.

Participants never paste prompts or logs. Maven writes each run with `-l` into
`outputs/evidence/`, and the skills read those files by path. Qualify that each role can read
that folder on the target installation. Maven writes no log if the folder is missing, so the
release must ship `outputs/evidence/README.md`. Custom-agent handoff buttons are not used:
VS Code supports them only in the Local harness, which is scheduled for removal in February 2027.

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

JetBrains announced its Copilot harness as generally available in August 2026 and default
when available in October 2026. Current GitHub documentation still lists individual JetBrains
customizations as preview. Harness availability does not establish feature availability.
Qualify each feature in the actual approved plugin and tenant. Native skills may require an
approved update if the existing version lacks them; retain the qualified fallback for delivery.
VS Code lists removal of the Local harness in February 2027. Recheck before future delivery.

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
| Client VS Code | Record | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |
| Client IntelliJ | Record | Record | Record | Record | Pending | Pending | Pending | Not yet qualified |

Historical dry run established compile/test and Copilot analysis on selected machines. It did
not establish full execution of this revised workflow or Spring Boot startup. Rehearse the
exact release. No external scanner, Python, Copilot CLI, hook, or MCP server is required.
