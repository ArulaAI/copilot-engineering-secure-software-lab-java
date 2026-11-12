# AI and agentic security reference

Verified against official OWASP resources on November 12, 2026. The application and AI-assisted
engineering workflow are separate threat-model subjects. Use edition-qualified identifiers.
The course mappings below are our instructional application, not an official OWASP crosswalk.

## Framework selection

| Reference | Verified edition | Course use |
| --- | --- | --- |
| Web Application Top 10 | 2025 | Java refund authorization and other application-code findings |
| Top 10 for Agentic Applications | 2026, released December 9, 2025 | Goals, tools, authority, context, and consequences in the engineering workflow |
| Top 10 for LLM Applications | 2026 | Untrusted model inputs, generated output, disclosure and consumption |
| Securing Agentic Applications | V2, PDF dated October 2026, resource published November 10, 2026 | Architecture, action authorization, trust boundaries and assurance guidance |

The LLM resource page is dated August 3, 2026 and the downloadable document August 4.
Its table of contents and final ranking table establish the 2026 identifiers. Older individual
risk pages still show 2025 labels. Do not combine their numbers with the current edition.
For example Excessive Agency is LLM03:2026 and Unbounded Consumption is LLM06:2026.
STRIDE remains a method for reasoning about threats; the Top 10 lists supply complementary
risk coverage. A category label is neither a test result nor a compliance certification.

## Agentic Top 10 coverage decisions

| 2026 ID and risk | Relevance to this course |
| --- | --- |
| ASI01 Agent Goal Hijack | A source/issue excerpt tries to redirect the selected review into changing tests |
| ASI02 Tool Misuse and Exploitation | An allowed file or terminal tool is used for an unintended target or operation |
| ASI03 Identity and Privilege Abuse | A role inherits authority it was not meant to hold; inspect effective tools and human delegation |
| ASI04 Agentic Supply Chain Vulnerabilities | Conditional risk if repository skills, profiles or connected components are changed by an untrusted source |
| ASI05 Unexpected Code Execution (RCE) | Conditional impact if generated or retrieved commands reach an execution tool without proper authorization |
| ASI06 Memory & Context Poisoning | Consider corrupted retained context and its provenance; no persistent memory system is implemented here |
| ASI07 Insecure Inter-Agent Communication | Native agent-to-agent protocols are outside scope; human-mediated evidence handoffs are not proof of A2A security |
| ASI08 Cascading Failures | An unsupported finding or fabricated test claim propagates into a patch and approval |
| ASI09 Human-Agent Trust Exploitation | A confident summary persuades a person to approve without checking evidence |
| ASI10 Rogue Agents | Autonomous deviation requires separate analysis; a mistaken answer alone does not establish a rogue agent |

Do not report all ten as confirmed vulnerabilities. Mark relevant hypotheses, observed behavior,
conditional risks, and out-of-scope features separately. The current profiles and skills express
intended capabilities. Actual Copilot tool availability and enforcement require target rehearsal.

## Relevant LLM 2026 risks

| Identifier | Course review question |
| --- | --- |
| LLM01:2026 Prompt Injection | Can an untrusted source comment, issue or tool result redirect the task? |
| LLM02:2026 Sensitive Information Disclosure | Could sensitive context be exposed through responses or actions? Use synthetic data only. |
| LLM03:2026 Excessive Agency | Does the role have unnecessary functions, authority or autonomy? |
| LLM04:2026 Supply Chain | Who can change the loaded model/component/skill definitions? |
| LLM06:2026 Unbounded Consumption | Can repeated scans, tools or repair loops exhaust the time/usage budget? |
| LLM07:2026 Misinformation | Does a false finding or fabricated execution result drive a consequential decision? |
| LLM10:2026 Improper Output Handling | Is generated code or a command validated before downstream use? |

LLM05:2026 Data and Model Poisoning, LLM08:2026 Hidden Context Exposure, and LLM09:2026 Vector
and Embedding Weaknesses remain additional applicability questions. No training/fine-tuning,
RAG store, hidden-prompt security test or embedding pipeline is implemented by this lab.
Absence from the fixture is not proof that a real deployment is protected.

## Threat-model enhancements

Create two small views. View A maps the Java caller, trusted Principal fixture, ownership
decision and refund state. View B maps the learner's selected task, repository/context inputs,
Copilot role/model boundary, configured tools, proposed code/commands, review and approval.
Mark components implemented, assumed or proposed. Treat native model-provider behavior,
tool enforcement and storage as unverified unless observed in the approved environment.

For View B record input provenance and trust, the principal on whose behalf a tool acts,
permitted target/operation, effective tool evidence, output destination and potential blast
radius. Record whether a control is advisory instruction, host-enforced restriction,
application check or human review. Attach evidence for any claimed enforcement and leave
unknowns open. Schema/format conformance or human agreement does not authorize an action.
Use least agency for the exercise, with one selected phase, minimum necessary tools and an
explicit gate for side effects. Existing host/application policies must authorize the actual
operation and resource at the action sink. Approval of a plan does not authorize changed targets
or parameters. The course records those enforcement boundaries rather than installing a policy engine.

Use AI-01 / T-AI-01 for the synthetic context challenge. It is a threat hypothesis until observed.
Trace the entry surface, attacker influence, possible goal change, action sink, consequence,
control and evidence. The required Java implementation remains REFUND-01 only. Its nine
acceptance checks do not establish prompt-injection resistance or other AI security properties.

## Official sources

- [OWASP Web Top 10 2025](https://owasp.org/Top10/2025/)
- [OWASP Top 10 for Agentic Applications 2026](https://genai.owasp.org/resource/owasp-top-10-for-agentic-applications-for-2026/)
- [Agentic 2026 official document](https://genai.owasp.org/download/52117/)
- [OWASP LLM Top 10 2026](https://genai.owasp.org/resource/owasp-genai-llm-top-10-2026/)
- [LLM 2026 official document](https://genai.owasp.org/download/56857/)
- [Securing Agentic Applications V2](https://genai.owasp.org/resource/securing-agentic-applications-v2/)
- [AI threat modeling guidance](https://genai.owasp.org/glossary/ai-threat-modeling/)
- [Multi-Agentic System Threat Modeling Guide v1.0](https://genai.owasp.org/resource/multi-agentic-system-threat-modeling-guide-v1-0/)

This is an independently written course reference with illustrative mappings and attribution.
Consult the linked publications for complete definitions, mitigations, and licensing terms.
