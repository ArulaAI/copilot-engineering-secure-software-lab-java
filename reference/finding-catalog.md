# Facilitator finding catalog

Facilitator only. Use it to assess Stage 1B and Stage 2, not as an answer key to hand out.
Learners may group, name and score differently; credit evidence and reasoning, not matching IDs.
Severities are suggested. The code carries no comments that label problems, so learners must
trace code paths; method and constant names (for example `unsafeParseToken`, `SECRET_KEY`) still
give some hints, as names do in real code.

## Root causes

| # | Root cause | Main locations | OWASP 2025 | Suggested severity |
| --- | --- | --- | --- | --- |
| 1 | Command injection | ModernApiHandler.executeCommand; UserRepository.exportUsers | A05 Injection | Critical |
| 2 | SQL injection (**SQLI-01** = findByEmail, searchUsers) | UserRepository: findByEmail, searchUsers, findByQuery, createUser, updateUser, deleteUser, batchUpdateRoles; ModernApiHandler.searchWithTextBlock; QueryBuilder (all builders) | A05 Injection | Critical |
| 3 | Unsafe deserialization and type confusion | UserRepository.loadUserData; ModernApiHandler.processObject | A08 Software or Data Integrity Failures | Critical |
| 4 | Forgeable tokens | TokenManager: hard-coded "secret", accepts unsigned tokens, parses without verification, refreshes unverified tokens | A04 Cryptographic Failures / A07 Authentication Failures | Critical |
| 5 | Missing authentication and object authorization | UserApi (list, get, update, delete, resetAllPasswords, bulkCreate); PaymentHandler (refund, get, list, delete); ResourceController (get, updatePermissions, delete); AuthController (getUser, updateUser) | A01 Broken Access Control | High |
| 6 | Path traversal (**PATH-01** = readFile, deleteFile, listFiles) | FileHandler: readFile, writeFile, listFiles, deleteFile, moveFile, extractZip (zip slip); UserRepository avatar methods; ModernApiHandler readFile, writeFile, readLogs; ResourceController.includeFile | A01 Broken Access Control | High |
| 7 | SSRF | FileHandler.fetchFromUrl, readUrlContent (file://); ResourceController.fetchExternalResource, proxyRequest; ModernApiHandler.proxyRequest, asyncFetch | A01 Broken Access Control | High |
| 8 | Weak password storage | PasswordHandler (MD5, SHA-1, Base64, XOR, timing-unsafe compare); AuthController.md5Hash; seeded plain-text passwords in UserApi | A04 Cryptographic Failures | High |
| 9 | Session management | SessionManager: sequential IDs, fixation, no expiry, all sessions exposed | A07 Authentication Failures | High |
| 10 | Vulnerable dependency | pom.xml jackson-databind 2.9.8 (CVE-2019-12384) | A03 Software Supply Chain Failures | High |
| 11 | Secrets and card data in logs | PaymentHandler (card numbers), AuthController (passwords), TokenManager (tokens), query logging | A09 Security Logging and Alerting Failures | Medium |
| 12 | Unsigned webhook | PaymentHandler.handleWebhook | A08 Software or Data Integrity Failures | Medium |
| 13 | Debug endpoints exposed | UserApi.debugAllUsers; ResourceController.debugInfo | A02 Security Misconfiguration | Medium |
| 14 | Predictable tokens and keys | PasswordHandler.generateResetToken; TokenManager.createResetToken, generateApiKey (Math.random) | A04 Cryptographic Failures | Medium |
| 15 | Open redirect | ResourceController.handleRedirect | A01 Broken Access Control | Medium |
| 16 | Upload type checks by extension and deny-list | FileHandler.isAllowedFileType, getContentType | A06 Insecure Design | Medium |
| 17 | Business logic: refund larger than original | PaymentHandler.processRefund | A06 Insecure Design | Medium |
| 18 | Password exposed via record toString; shared state in parallel stream | ModernApiHandler UserRecord, processBatch | A09 / A10 | Low |

Plausible false positives learners may reject: `QueryBuilder` only builds strings (risk depends on
callers executing them); `getContentType` alone is not exploitable without an upload path;
`createTempFile` returns a File without creating it.

## Variants of the two lab fixes

Expect Stage 3 change plans to list these as left unfixed: UserRepository findByQuery,
createUser, updateUser, deleteUser, batchUpdateRoles (SQL); FileHandler writeFile, moveFile,
extractZip (paths); plus the cross-class locations above.
