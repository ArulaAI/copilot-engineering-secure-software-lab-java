---
name: security-review
description: "Explain the simple lab workflow without doing extra work."
argument-hint: "Optional workflow question"
user-invocable: true
disable-model-invocation: true
---

Local entry point: `/lab-workflow` selects **OWASP Security Expert** and loads this procedure. Direct `/security-review` requires that agent to be selected first.

Explain the next action from available context: map, investigate, threat-model, Create the named fix,
Critique it, or final review. The guide supplies implementation and review prompts. No task ledger,
plan, cycle counter or metadata repair is required. Participants choose tasks; agents do not auto-start
others. Generic /hand-off can record any state when requested. Do not perform a stage or alter files.
