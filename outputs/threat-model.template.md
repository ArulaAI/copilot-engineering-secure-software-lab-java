---
stage: 2-threat-model
threats_total: ""         # number of threats in the table
top5: []                  # threat IDs in priority order, e.g. [T-01, T-02, T-03, T-04, T-05]
evidence: [outputs/codebase-map.md, outputs/findings.md]
gate: ""                  # pass | fail
---

# Threat model

Basis values: **observed** (seen in a cited file or log), **inferred** (reasoned from observed
facts), **assumed** (not shown by this repository). Unknown is a valid answer.

Start from the architecture and trust boundaries in codebase-map.md. Group findings that an
attacker would use the same way into one threat.

## Threats

| ID | Threat | Findings | Entry point | STRIDE | Severity | Impact (1-3) | Likelihood (1-3) | Priority (impact x likelihood) | Evidence |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| T-01 | {{attacker goal and method}} | {{F-xx, ...}} | {{entry point}} | {{category}} | {{Critical/High/Medium/Low with rationale}} | {{1-3}} | {{1-3}} | {{1-9}} | {{file:line}} |

Impact: 3 = data of all users or code execution, 2 = one user's data or a privileged action,
1 = limited disclosure. Likelihood: 3 = reachable by anyone with no precondition, 2 = needs an
account or a known identifier, 1 = needs unusual access.

## Blast radius: top 5

| Threat | Who can exploit it | What they reach | Action | Repeatable? | Would anyone notice? | Evidence | Basis |
| --- | --- | --- | --- | --- | --- | --- | --- |
| {{T-xx}} | {{anonymous / any user / insider}} | {{one record / one user / all users / the host}} | {{read / change / delete / execute}} | {{yes, why}} | {{logging present / absent / unknown}} | {{file:line}} | {{basis}} |

## Top 5 to mitigate first

| Rank | Threat | Why it ranks here | Mitigation | Acceptance criteria / proposed scope |
| --- | --- | --- | --- | --- |
| 1 | {{T-xx}} | {{priority, blast radius}} | {{one sentence}} | {{positive and negative checks; implementation is not yet complete}} |

## Gate

- [ ] Every threat traces to findings and an entry point in the code.
- [ ] Priority uses the impact and likelihood scales above, with evidence.
- [ ] Each top-5 blast radius answer cites code or is marked assumed or unknown.
- [ ] The selected five arise from evidence-based priorities and have actionable scope and acceptance criteria.
- [ ] All other findings remain visible, and no proposed mitigation is labelled implemented.
