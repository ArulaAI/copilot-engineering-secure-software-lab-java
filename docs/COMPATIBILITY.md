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

Record the actual VS Code version, Copilot extension version, selected harness and model. The
primary route is Copilot Chat with the Copilot harness. Confirm six agents in the picker (Package
Explorer is a hidden subagent) and six skills (five stage commands and /security-review). Learners
pick an agent, type the command and attach nothing: each skill reads its inputs by path. Check
chat.useAgentSkills and actual skill discovery. The github.copilot.chat.cli.customAgents.enabled
setting may need enabling in supported versions. Use only settings the organization allows.

Expected tools, to confirm in observed activity:

| Agent | Tools | Writes |
| --- | --- | --- |
| Codebase Mapper | read, search, edit, agent | outputs/codebase-map.md, outputs/handoff.md |
| Package Explorer (hidden) | read, search | nothing |
| Security Vulnerability Hunter | read, search, edit | outputs/findings.md, outputs/handoff.md |
| STRIDE Threat Modeler | read, search, edit | outputs/threat-model.md, outputs/handoff.md |
| Secure Remediation Engineer | read, search, edit, execute | the assigned methods, outputs/change-plan.md, outputs/handoff.md |
| Secure Code Reviewer | read, search, edit, execute | outputs/review.md, outputs/handoff.md, one DataRedTeamCheck class |
| OWASP Security Expert | read, search | nothing |

The "writes" column is an instruction, not something the harness enforces: an agent with edit
can technically change any file. Learners check Source Control after each stage; only the listed
files should change. No forked skill, experimental setting, Copilot CLI or extra runtime is required.

The workflow depends on two platform behaviors:

1. **Subagents.** Codebase Mapper calls Package Explorer once per package (`agents:` plus the
   `agent` tool). If the organization blocks subagents, the Mapper reads each package itself,
   one at a time, and says so in its report. Mapping then takes longer.
2. **Running commands from chat.** Secure Remediation Engineer and Secure Code Reviewer run Maven
   and git with the learner's approval. If that is blocked, learners run the commands themselves.

`OutputsCheck` and `*RedTeamCheck` classes do not end in `Test`, so the default build never runs
them and the documented counts (10 run, 6 skipped; 10 run, 0 skipped) are unchanged.

## Limits to know (facilitator only)

These are kept out of the learner guide on purpose. Raise them in the debrief or when asked.

- A role or skill does not grant permissions; the harness's tool list does. Verify effective
  tools in rehearsal, not from the agent file.
- A new chat or a subagent reduces carried-over context. It is not process or OS isolation and
  does not prove independent judgment.
- Approving a command is a human checkpoint. It complements, and does not replace, host
  restrictions.
- Agents record command results as summary lines in their reports. The reviewer rerunning the
  tests itself is the check against a misreported result.
- A hand-off section is selective and can omit facts. The stage reports remain the source of truth.
- `/compact` summarizes; details from earlier turns can be lost.

## Manual fallback

If skills are unavailable, attach the relevant phase SKILL.md and send the single line
`Follow the attached procedure.` The skill links the shared contract and its inputs. Rehearse this
route and label it. Custom-agent handoff buttons are not used: VS Code supports them only in the
Local harness, which is scheduled for removal in February 2027.

## IntelliJ IDEA

Record IDEA and Copilot plugin versions, available agent mode/customizations, and model.
Use the plugin's context-attachment controls to select the scenario, source, and tests.
Check actual attachments before submitting. Do not rely on VS Code #file or @workspace syntax.
If custom agents or skills are unavailable, stop for the facilitator and use the rehearsed
one-line fallback with the existing phase SKILL.md and role instructions attached. Attachment
alone does not enforce permissions. Keep analysis non-editing, use participant-run Maven when
required, and save reports manually if the agent cannot write them. If the reviewer's tools
cannot be established, use facilitator-led review and label unverified checks.

## Model and result expectations

Use the model approved for the session and available to all participants. No model is pinned
in repository agents. Choose from the existing organization's allowed models. Record the exact
choice. Pending target-IDE/subagent/permission qualification and timed rehearsal remain delivery gates. Do not request paid upgrades or guarantee identical findings from different models.

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
