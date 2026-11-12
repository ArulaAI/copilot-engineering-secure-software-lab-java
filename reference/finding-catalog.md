# Validated course finding catalogue

| ID | Source and symbol | Status | Evidence | Mapping |
| --- | --- | --- | --- | --- |
| REFUND-01 | RefundService.refund | Intentional starter defect | Non-owner/admin-looking principals receive REFUNDED, two enabled acceptance checks fail | A01:2025 Broken Access Control |

There is one required finding. The corrected solution rejects unauthorized callers before
state changes. Rejected amount-validation hypotheses are not counted as findings. The historical
corpus is optional and has no certified total. Add a finding only after source and applicability
review, with a reproduction or explicit static-analysis limitation. Stable symbols are canonical
because line numbers change after edits. Record current line numbers in participant reports.
