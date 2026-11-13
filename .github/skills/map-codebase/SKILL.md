---
name: map-codebase
description: "Map the codebase before vulnerability judgments."
argument-hint: "Optional package to start with"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Select Codebase Mapper or stop and ask to switch. Record stage-entry base revision and Git status in the report. Map directly in this chat without delegation. Build the package/file inventory from source declarations. Read the launcher, POM, application properties and security configuration, then all relevant Java files. Keep source citations while reading and avoid a second complete scan; reopen only for a specific evidence gap. Distinguish declared configuration from actual runtime proof. Check actual call edges and externally controlled inputs against source. Do not invent an API-to-helper path. Write outputs/codebase-map.md from its template with components/edges labelled implemented, assumed or proposed, source citations and unknowns. Leave the human gate pending until decided. Tell the learner to check the report, then invoke /hand-off in this same chat with Hand-off Recorder. Do not update handoff yourself.
