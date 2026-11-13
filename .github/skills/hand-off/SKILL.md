---
name: hand-off
description: "Record an explicit end-of-stage handoff without asking for repeated summaries."
argument-hint: "Stage just completed"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. Skill invocation does not switch roles or grant tools.

Select Hand-off Recorder in the SAME chat as the stage being closed, or stop and ask to switch. Read that stage report, current task state and observed evidence. Use existing conversation decisions rather than requesting them again.

1. Infer the stage or use the supplied stage argument. Inspect base revision, pre-existing changes, current Git status/diff and untracked files. Discovery stages may have report-only changes. Do not infer a decision or test run from a diff.
2. Read current gate and task verdicts. A stale or missing approval stays pending; ask only if it blocks the next action. Do not change task status or counts.
3. Create outputs/handoff.md from its template if absent. Append a concise section for the stage: decision/gate, report paths, observed source/test changes and run evidence, selected task states/cycles, unresolved/deferred work, next action and inputs. Point to the ledger and reports rather than repeating them. Update last_stage. Record later corrections as a superseding section, preserving earlier entries.
4. Write ONLY outputs/handoff.md. Do not rewrite stage reports, source, tests or task states. Show the learner the saved section and next step. Begin the next stage in a fresh chat. No automatic commits, resets or pushes.
