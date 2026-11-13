---
name: generate-threat-model
description: "Model all supported findings and select evidence-ranked priorities."
argument-hint: "Optional business context"
user-invocable: true
disable-model-invocation: true
---

Read the [shared contract](../security-review/references/evidence-contract.md) and [scenario](../../../docs/SCENARIO.md). Read outputs/handoff.md when present, then current stage artifacts. In VS Code Local, run `/threat-model` to select **STRIDE Threat Modeler** automatically. Direct `/generate-threat-model` uses the current agent; select **STRIDE Threat Modeler** first.



## Input readiness

Read the available architecture and ALL original findings; use handoff only when present. Check coverage and preserve assessment, reachability and verification as separate dimensions. Fill small source-evidence gaps directly. State missing inputs and qualify priorities when coverage is incomplete; do not pause for approval or require a handoff. Retain useful work rather than repeating the entire scan. Do not write handoff.

## Model and score

1. Build a traceability register for every original finding, including conditional/standalone risks. Map it to threat IDs, or state a supported rejection/coverage gap with evidence. Preserve original finding IDs and uncertainty.
2. Apply STRIDE to actual service boundaries and resources. Describe attacker goals and concrete scenarios. Record actor, account/identifier/other prerequisites, existing controls, reachability, code assessment, verification and assumptions. An HTTP handler declaration is not observed public deployment.
3. Score impact and likelihood separately using the supplied template scales. Explain the evidence and explicit conditions behind each score. Credentials, valid object IDs, log/store access and missing integration are real prerequisites. Do not apply an anonymous/no-identifier score to every scenario because another endpoint in the group is easier.
4. When a threat groups scenarios with different preconditions, record each scenario's score. Name the representative scenario and rationale used in ranking; do not silently average, take a maximum or extend the easiest path's blast radius to every path. A missing caller affects confidence/likelihood without forcing Medium severity or suppressing the code-level risk. Put conditional impact in the rationale; keep Severity one of Critical, High, Medium, Low.
5. Populate blast radius for selected priorities using the same scenario and assumptions as the scores: who, resources, actions, repeatability and observability. Separate credential-dependent responses from anonymous reads and log-reader access. Capture unknowns rather than claiming absence of monitoring.
6. Rank the shortlist requested by the scenario from evidence, with tie-breaking rationale. No named vulnerability or preselected issue set is required. Use Web Top 10 2025 from docs/owasp-reference/top-10-summary.md; flag and reconcile mixed editions.

## Prepare bounded implementation scope

For each selected threat, define a concrete remediation objective with allowed code/helpers/new tests and positive/negative criteria. Separate independently fixable controls into distinct threats before ranking, or explicit subtasks with separate acceptance. Each subtask retains its parent threat/finding IDs and open remainder. Do not label an entire multi-control threat fixed after one subtask. The facilitator chooses live tasks later; do not predeclare everything else fixed or force completion counts.

## Consistency and output

Write outputs/threat-model.md from [its template](templates/threat-model.template.md). Check all finding/threat/scenario references, top-five membership and arithmetic. Check that scoring, blast radius, proposed scope and concluding disposition agree. A finding mapped to a selected threat cannot also be called wholly lower-ranked/deferred without identifying its remaining sub-scope. No proposed mitigation is implemented yet. Record source state and verification limits. Summarize priorities and uncertainty without an approval request. Handoff is optional; do not update handoff yourself.
