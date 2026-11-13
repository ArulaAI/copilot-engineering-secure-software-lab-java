---
stage: 3-critique
reviews_total: ""
evidence: [outputs/tasks.md, outputs/change-plan.md]
---

# Per-task critique history

Append each review. Do not overwrite earlier cycles. The reviewer never resets a cycle or fixes source.

## Task {{ID}}, cycle {{number}}

- Reviewed source state: {{base, current diff/status and relevant file hashes}}
- Agreed scope and original finding: {{references}}

| Criterion | Expected | Actual | Check / evidence | Result |
| --- | --- | --- | --- | --- |
| {{criterion, legitimate behavior, prior regression or adversarial case}} | {{expected}} | {{observed}} | {{named test and run path}} | {{pass/fail/not run}} |

| Command | Exit | Pass / fail / error / skip and named failures | Full evidence |
| --- | --- | --- | --- |
| {{executed command}} | {{exit}} | {{observed}} | {{path}} |

- Verdict: {{ACCEPTED / CHANGES REQUIRED / NEEDS EVIDENCE}}
- Concrete critique or missing evidence: {{issue and detecting check, or none}}
- Limits and residual risks: {{remaining gaps}}
- Next action: {{advance, correct within remaining cycles, or facilitator pause}}
