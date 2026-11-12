---
name: find-vulnerabilities
description: Investigate a bounded scope using source evidence.
agent: Security Vulnerability Hunter
---

Read docs/SCENARIO.md and inspect src/main/java/com/securelabs/lab/RefundService.java
and src/test/java/com/securelabs/lab/RefundSecurityTest.java. Investigate REFUND-01.
Describe the trusted identity boundary and identify a concrete unauthorized state change.
Also assess one plausible false positive, such as validated positive amount handling.
Do not use reference/ or a finding-count target. Return the findings template in chat.
Tell me the exact reproduction command and expected failure, without claiming execution.
