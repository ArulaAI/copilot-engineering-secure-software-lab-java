---
name: find-vulnerabilities
description: "Investigate mapped inputs and rank evidence-backed findings."
argument-hint: "Optional flow to investigate"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. In VS Code Local, run `/investigate` to select **Security Vulnerability Hunter** automatically. Direct `/find-vulnerabilities` uses the current agent; select **Security Vulnerability Hunter** first.



## Available context

Read outputs/codebase-map.md and outputs/handoff.md when present. Check the map against current source; fill small evidence gaps directly and state remaining coverage limits. A missing report, handoff or approval is not a reason to wait. If the map is absent, inspect the relevant source directly. Do not invent earlier evidence or write handoff.

## Whole-service investigation

1. Inventory all application classes and relevant configuration, starting from the map but checking for omissions. Include standalone helpers, utilities, resources and dependency declarations. Tests may supply local behavioral evidence; a test invocation is not a production call chain.
2. Review inputs, intended contracts, actual callers and sensitive operations across every class. Retain citations during the first read; consolidate location checks instead of rescanning the whole tree repeatedly.
3. For each supported issue record the missing control and code evidence separately from external reachability. Use code assessment, reachability and verification fields from the findings template. Standalone code can contain a confirmed defect even when production exposure is unknown. Do not label every dangerous helper defective merely because it exposes a powerful API.
4. Justify severity with impact and preconditions, explicitly qualifying hypothetical callers or deployment effects. Do not force named issues, finding counts, HTTP-only coverage or a default Medium rating for disconnected components.
5. Merge only the same root cause. Reject unsupported exploit claims with reasons, while retaining any separately supported underlying risk. Query text that is never executed is not demonstrated database injection. A missing production caller does not disprove an unsafe implementation.
6. Write a per-class/configuration coverage matrix with linked finding IDs, explained no-finding outcomes and explicit incomplete reviews. Reviewed does not mean vulnerable; absence of a finding does not prove security. No build, network probing or application startup is required in this role.

Write outputs/findings.md from [its template](templates/findings.template.md) and preserve the original discovery record. Record source state and evidence without claiming tests were run. Summarize findings and coverage limits without asking for approval. Handoff is optional. Do not update handoff yourself.

Use the course mapping in docs/owasp-reference/top-10-summary.md for application findings: Web Top 10 2025. Read it once and retain edition-qualified IDs. Do not copy older IDs from source comments or memory. If mapping is uncertain, explain the gap rather than invent a category. Keep the severity field to Critical, High, Medium or Low; put conditional impact and uncertainty in the rationale.
