# OWASP Top 10 2025 course reference

OWASP Top 10 is an awareness taxonomy. Use evidence and behavioral acceptance criteria to
verify a fix. Category IDs require an edition. Historical source comments retain 2021 IDs.

| 2025 category | Course relevance | Previous 2021 category where applicable |
| --- | --- | --- |
| A01 Broken Access Control | Object ownership, path traversal, SSRF | A01 and A10 |
| A02 Security Misconfiguration | Missing configuration and unsafe defaults | A05 |
| A03 Software Supply Chain Failures | Dependency manifests and approved artifact provenance | A06, expanded scope |
| A04 Cryptographic Failures | Keys, hashing, signature primitives | A02 |
| A05 Injection | SQL values versus identifiers | A03 |
| A06 Insecure Design | Business rules and trust assumptions | A04 |
| A07 Authentication Failures | Trusted upstream identity, session lifecycle | A07 |
| A08 Software or Data Integrity Failures | Webhook integrity and verification | A08 |
| A09 Security Logging and Alerting Failures | Sensitive data exposure and security events | A09 |
| A10 Mishandling of Exceptional Conditions | Fail-open errors and resource handling | New category |

For SQL injection, A05:2025 and CWE-89 fit; for path traversal, A01:2025 and CWE-22. Explain the concrete
missing control before assigning a category. Do not infer severity from category alone.
For ASVS, use a supplied versioned requirement and explain applicability. No blanket compliance claim.

UUID.randomUUID uses a cryptographically strong pseudorandom generator. That does not establish
session lifecycle security. HMAC verification does not establish event idempotency. A PreparedStatement
does not bind table or column names, so approved identifiers need an allowlist. A vulnerable version
in a manifest requires applicability analysis and current approved advisory evidence.

Sources checked for this release:

- [OWASP Top 10 2025](https://owasp.org/Top10/2025/)
- [OWASP ASVS](https://asvs.owasp.org/)
- [OWASP SSRF prevention](https://cheatsheetseries.owasp.org/cheatsheets/Server_Side_Request_Forgery_Prevention_Cheat_Sheet.html)
- [Java UUID API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/UUID.html)

No unsupported Copilot detection-accuracy rating or fixed vulnerability total is used.

## AI-assisted workflow coverage

Use the separate [AI and agentic reference](ai-security.md) when assessing the engineering
assistant's inputs, tools, authority and outputs. It records the current Agentic 2026 and LLM
2026 editions. Keep their identifiers separate from Web 2025 and historical Web 2021 comments.
STRIDE plus the relevant AI coverage questions supports threat modeling without asserting that
the Java application implements an AI architecture.
