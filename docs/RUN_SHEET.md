# Facilitator run sheet

| Clock | Activity | Cue |
| --- | --- | --- |
| 0 to 20 | Introduction and demo | Evidence chain, roles, context, save report |
| 20 to 35 | Investigate | Two expected failed ownership checks, FLEX on extra findings |
| 35 to 45 | Model | Implemented versus assumed flow, T-REFUND-01 |
| 45 to 70 | Implement | Agreed plan, only RefundService.java changes |
| 70 to 85 | Verify | Nine passing enabled checks, fresh review, NEVER CUT |
| 85 to 90 | Debrief | Transfer questions, NEVER CUT |

Before the room opens, complete the environment record and release verification. Assign
one person to help-room support during introduction. Confirm chat and breakout controls.

Commands. Baseline `mvn clean test`. Reproduce `mvn -Dlab.verify=true -Dtest=RefundSecurityTest test`.
Final `mvn -Dlab.verify=true clean test`. Offline option is -o with existing cached artifacts.

Tools. Hunter investigates. Threat Modeler explains flow. Remediation Engineer edits after the
participant selects scope. Reviewer reads fresh evidence. OWASP Expert explains mappings.
Local prompts are shortcuts. The skill defines the procedure. Save reading-role output manually.

If asked why findings differ, compare source, assumptions, and behavior. Never promise complete
or identical discovery. If asked whether the application is secure, state the selected scenario
and untested boundaries. If blocked on packages, use the approved support route and the existing
validated test path. Do not introduce an installation during the session.

Closing sentence. Apply the procedure to your own trust boundaries and require evidence for each fix.
