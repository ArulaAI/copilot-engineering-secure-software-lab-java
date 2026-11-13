**Verdict: {{PASS / PARTIALLY FIXED / FAIL / NOT VERIFIED}}** — {{one-sentence reason and exact reviewed scope}}

# Security Review

## 1. Executive Summary and Scope

- Reviewed tasks and application/supporting files: {{actual completed scope}}
- Inputs: {{original findings, threat model, task critique results and current diff}}
- Review basis: {{source inspection and actual/reused tests; no deployment certification}}
- Outcome: {{completed improvements, required corrections and principal limitations}}

## 2. Vulnerabilities Fixed

| Original finding / threat | File / control changed | Remediation | Verification evidence | Current status |
| --- | --- | --- | --- | --- |
| {{ID or named issue}} | {{file:line/control}} | {{actual implementation}} | {{named test/run or explicit gap}} | {{verified fixed / partial / not verified}} |

Do not treat passing tests as proof that unrelated controls or an entire grouped threat are resolved.

## 3. Remaining Issues and Required Changes

| Issue | Severity / conditions | Current status | Impact | Required correction or follow-up |
| --- | --- | --- | --- | --- |
| {{finding or review gap}} | {{justified severity and exposure assumptions}} | {{unstarted / partially fixed / failed / not verified}} | {{impact}} | {{next action}} |

Separate corrections blocking acceptance of completed scope from lower-ranked/unstarted work.
If no in-scope correction is required, say so; do not imply the service has no remaining weaknesses.

## 4. ASVS Gaps and Production Assurance

| Control area | Evidence available | Gap / assessment limit | Follow-up |
| --- | --- | --- | --- |
| {{authentication, authorization, validation, data protection, etc.}} | {{observed evidence}} | {{not tested or remaining concern}} | {{production check}} |

Use requirement IDs only from a supplied versioned ASVS source. Otherwise state that this is a
control-area gap discussion, not a formal ASVS assessment or compliance certification. Distinguish
method-level tests from full MVC, deployment, identity infrastructure and external-provider behavior.

## 5. Build Status and Test Evidence

| Command / evidence source | Executed now or reused | Total | Passed | Failed | Errors | Skipped | Result / scope |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| {{actual command/report}} | {{provenance and why reuse is valid}} | {{count or not run}} | {{count}} | {{count}} | {{count}} | {{count}} | {{result}} |

Passed = total minus failures, errors and skips. Report missing runs as not run. Count a focused run
and a regression run separately; do not add overlapping test executions into a fake unique total.
Record whether a separate build/compile was run or compilation occurred within Maven test. Relevant
skips do not prove acceptance; unrelated seeded checks remain open. No manufactured build result.

## 6. Final Decision and Next Steps

- Verdict for completed scope: {{same verdict as first line}}
- Blocking corrections: {{specific issues or none}}
- Remaining tasks / risks: {{open work without formal deferral requirements}}
- Unverified boundaries: {{explicit limits}}
- Recommended next action: {{accept completed lab scope, correct a gap or obtain missing evidence}}
