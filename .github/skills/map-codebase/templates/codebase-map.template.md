---
stage: 1a-map
packages_mapped: ""       # number of packages in the index, e.g. 4
entry_points: ""          # number of externally reachable entry points found
evidence: []              # files you want the next stage to open first
---

# Codebase map

Basis values: **observed** (seen in a cited file or log), **inferred** (reasoned from observed
facts), **assumed** (not shown by this repository). Unknown is a valid answer.

## Index

| Package | Files | Responsibility | Key classes | Evidence |
| --- | --- | --- | --- | --- |
| {{package}} | {{count}} | {{one line}} | {{classes}} | {{path}} |

## Entry points

Everything an outside caller can reach: HTTP endpoints, public methods called by them, schedulers.

| # | Entry point (method and path, or class#method) | Handler | Untrusted inputs | Authentication or authorization seen | Evidence |
| --- | --- | --- | --- | --- | --- |
| 1 | {{GET /api/...}} | {{Class#method}} | {{path variable, body field, header}} | {{none / check at file:line}} | {{file:line}} |

## Data stores, files and external calls

| Resource | Accessed from | How (SQL, file path, URL, process) | Evidence | Basis |
| --- | --- | --- | --- | --- |
| {{database / folder / URL / shell}} | {{Class#method}} | {{mechanism}} | {{file:line}} | {{basis}} |

## Architecture

Label each component and edge **implemented** (cite source), **assumed** or **proposed**.

{{Generate the Mermaid graph from actual source connections. No completed architecture is supplied.}}

## Trust boundaries

| Boundary | Where untrusted data crosses it | Evidence |
| --- | --- | --- |
| {{e.g. HTTP request to controller}} | {{Class#method}} | {{file:line}} |

## Unknowns

{{What the code does not show: deployment, configuration, real authentication.}}

## Quality notes

- Every package in src/main/java appears in the index.
- Entry points cite file and line; inputs are named.
- The diagram shows nothing as implemented that the code does not contain.

## Stage entry state

{{Base SHA and pre-existing Git status/diff. No commit or reset required.}}
