---
stage: 2-threat-model
threats_total: ""
top5: []
evidence: [outputs/codebase-map.md, outputs/findings.md]
gate: ""                  # human decision; pending drafts are not completed reports
---

# Threat model

Use ALL supported findings, including standalone components, and verify coverage before claiming a
complete shortlist. Keep observed, inferred and assumed facts separate. Source inspection is not live
exposure or executed verification. Use Web Top 10 2025 where application mappings are needed.

## Input coverage and assumptions

{{Accepted inputs, class/configuration coverage, missing evidence and explicit exposure/caller assumptions.}}

## Finding traceability and current disposition

Every original finding must appear, with multiple threat IDs where justified. Do not silently drop
conditional risks or describe a selected finding as wholly deferred elsewhere.

| Finding | Code assessment | Reachability | Verification | Threat IDs | Disposition / gaps and evidence |
| --- | --- | --- | --- | --- | --- |
| {{original ID}} | {{confirmed defect / conditional risk / needs evidence}} | {{demonstrated caller / standalone / unknown}} | {{source / test / runtime with provenance}} | {{IDs or reason unmapped}} | {{mapped, conditional, rejected claim or coverage gap}} |

## Threats

| ID | Attacker goal / threat | Findings | STRIDE | Severity | Potential impact and conditions | Existing controls / gaps | Evidence |
| --- | --- | --- | --- | --- | --- | --- | --- |
| {{threat ID}} | {{goal and mechanism}} | {{finding IDs}} | {{category}} | {{Critical / High / Medium / Low}} | {{impact and uncertainty}} | {{observed controls, not inferred runtime protection}} | {{file:line}} |

## Attack scenarios and scoring

Impact: 3 = all-users' data or code execution, 2 = one user's data or a privileged action,
1 = limited disclosure. Likelihood: 3 = no account or known identifier required in the scoped path,
2 = account or known identifier needed, 1 = restricted access or unproven caller integration needed.
These are conditional ordinal comparisons, not measured probabilities. Declare deployment/exposure
assumptions separately. If evidence is insufficient to justify a score, record the gap and seek
reconciliation instead of inventing precision.

| Scenario | Threat | Actor, entry and prerequisites | Existing controls / assumptions | Impact | Likelihood | Product | Rationale and evidence |
| --- | --- | --- | --- | --- | --- | --- | --- |
| {{scenario ID}} | {{threat ID}} | {{account, ID, log/store access, caller integration}} | {{controls and conditions}} | {{1-3}} | {{1-3}} | {{impact x likelihood}} | {{basis}} |

If scenarios under one threat differ, keep separate rows. Identify the representative scenario and
reason for its threat ranking below. Do not silently average/max scores or apply one endpoint's
access assumptions to other endpoints.

## Blast radius of selected priorities

| Threat / scored scenario | Who can exploit it and under what conditions | Resources reached | Action | Repeatability | Observability | Evidence / basis / unknowns |
| --- | --- | --- | --- | --- | --- | --- |
| {{IDs}} | {{anonymous / authenticated / log reader / component caller}} | {{bounded scope}} | {{action}} | {{evidence or uncertainty}} | {{observed / unknown}} | {{citations and assumptions}} |

## Top five priorities

| Rank | Threat | Representative scenario / score | Why this order, including tie-break | Mitigation objective | Remaining uncertainty |
| --- | --- | --- | --- | --- | --- |
| {{rank}} | {{ID}} | {{scenario and justified product}} | {{evidence-based order}} | {{objective, not a claimed fix}} | {{gaps}} |

## Bounded remediation tasks

Decompose independently fixable controls when necessary. Each task preserves its parent threat and
finding IDs. Completing a scoped task leaves other controls/locations explicitly open. The facilitator
chooses how many tasks to complete live; task count is not capped in the agents.

| Proposed task | Parent threat / findings | Allowed source/helpers/tests scope | Positive criterion | Negative criterion | Parent remainder after this task |
| --- | --- | --- | --- | --- | --- |
| {{task ID}} | {{IDs}} | {{bounded files/behavior}} | {{legitimate behavior}} | {{attack rejection}} | {{remaining locations/controls, or none if full scope}} |

## Consistent closeout

{{Selected priorities and proposed tasks; all lower-ranked, conditional or unmapped findings; no
implementation claims. Reconcile this text with the traceability table and top5 metadata.}}

## Source state and verification limits

{{Revision, tracked/untracked changes, source identity and actual verification level.}}

## Gate

- [ ] Whole-service findings are consumed or coverage gaps explicitly block a completeness claim.
- [ ] Every finding and selected threat has consistent traceability and disposition.
- [ ] Scenario scores match prerequisites, controls, assumptions and arithmetic.
- [ ] Blast radius uses the same actor/conditions as the scored scenario.
- [ ] Priorities are evidence-ranked without prescribed vulnerability types.
- [ ] Proposed tasks are bounded; partial work cannot close a whole multi-control threat.
- [ ] No fix, test result or human approval is invented.
