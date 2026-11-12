# Threat model

## View A. Java application

Finding and threat IDs. [REFUND-01 / T-REFUND-01]
Protected asset. [asset]
Diagram. [small Mermaid or text with implemented/assumed/proposed labels]
Source evidence for components and edges. [paths and symbols]
Trust boundary and relevant STRIDE categories. [reasoning]
Versioned Web category. [applicable Axx:2025 and rationale]
Impact, exploit preconditions, existing controls. [evidence]
Selected mitigation and linked Java acceptance checks. [decision]
Open assumptions and proposed controls. [limits]

## View B. AI-assisted engineering workflow

Finding/threat ID and status. [AI-01 / T-AI-01, hypothesis / observed / untested]
Protected asset. [code, test integrity, evidence, permitted task or sensitive context]
Small flow. [learner task, context input, model/role, tools, output and approval]
Component/flow status. [implemented / assumed / proposed, with evidence]

| Input or artifact | Origin and trust | Attacker influence | Where it enters context | Evidence |
| --- | --- | --- | --- | --- |
| [source, issue or log] | [provenance] | [control] | [entry point] | [observation or unknown] |

| Actor/tool/action | Intended authority and target | Effective authority observed | Enforcement evidence or unknown |
| --- | --- | --- | --- |
| [role or tool] | [principal, operation, permitted resource] | [observed / untested] | [host setting/activity or none] |

Attack path. [entry surface, goal change, action sink, impact and blast radius]
Mappings and editions. [relevant ASIxx:2026 / LLMxx:2026, rationale, no forced quota]

| Control | Type | Validation and actual observation | Residual risk or next check |
| --- | --- | --- | --- |
| [control] | [advisory instruction / host restriction / application check / human review] | [response/activity/diff or untested] | [remaining gap] |

Inapplicable or absent features. [RAG, training pipeline, persistent memory, native A2A, etc.]
Evidence limits. [text response versus observed actions, no general injection-resistance claim]
Separate decisions. [REFUND-01 code fix; AI hypothesis/control validation without code changes]
