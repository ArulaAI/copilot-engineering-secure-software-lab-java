# Acceptance evidence matrix

Finding and threat. [REFUND-01 / T-REFUND-01]
Run revision or diff. [identify the implementation that produced the output]
Command, exit status, totals, full log location. [actual evidence and accessible log]
Execution origin. [personally executed / participant-supplied logs inspected]

| Criterion | Source decision and current lines | Behavioral check | Before evidence | After evidence | Reviewer status or missing evidence |
| --- | --- | --- | --- | --- | --- |
| Non-owner denied, owner still succeeds | [location] | anotherCustomerCannotRefundAndOwnerStillCan | [actual result] | [actual result] | [status] |
| Admin-looking name grants no access | [location] | adminLookingNameGrantsNoPrivilege | [actual result] | [actual result] | [status] |
| Owner can refund once | [location] | ownerCanRefundOnce | [actual result] | [actual result] | [status] |
| Missing identity does not consume refund | [location] | unauthenticatedRequestDoesNotConsumeRefund | [actual result] | [actual result] | [status] |
| Invalid request preserves state | [location] | invalidRequestsLeavePaymentRefundable | [actual result] | [actual result] | [status] |
| Concurrent requests produce one refund | [location] | concurrentRequestsProduceExactlyOneRefund | [actual result] | [actual result] | [status] |

Full acceptance also includes the three existing webhook checks. Record their outcomes and any
errors or skips. Mark missing evidence rather than filling assumed results. The initial targeted
reproduction executes only the first two checks, so mark other before results not run unless
baseline evidence is attached. In-process results do not prove HTTP authentication or persistence.
