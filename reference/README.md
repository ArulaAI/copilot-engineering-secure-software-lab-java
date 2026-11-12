# Facilitator resources

These resources are outside src/ and do not compile into the training application.
Do not consult them during participant discovery, planning, implementation, or review.
They are visible in the public repository, so this is an educational boundary only.

## Reviewed solution

solutions/RefundService.java adds the ownership rejection to the course starter. In a disposable
checkout copy it over src/main/java/com/securelabs/lab/RefundService.java and run
`mvn -Dlab.verify=true clean test`. Nine checks must pass with zero skipped.
Restore the starter before distributing the exercise. See docs/VERIFICATION.md for mutation checks.

The solution uses an upstream trusted Principal fixture. It implements one full refund per
transaction in a single JVM. No administrator override, HTTP authentication, persistent
storage, distributed locking, or external settlement is established by this fixture.

## Historical archive

archive/legacy-secure/ contains retired examples previously labelled secure. They contain known
unsafe authentication, session, webhook, SSRF, and concurrency patterns. Their original claims
are historical evidence and are not endorsed. Do not compile, copy, or use them as solutions.
archive/illustrative-tests/ contains retired tests that did not exercise the application.
archive/chatmodes/ contains superseded customization formats and model-era guidance.
archive/answer-key-2021.md contains an unvalidated historical catalogue with stale counts,
line references, UUID claims, and a removed Log4j dependency. Never use its totals as an assessment.

The existing POM is retained to preserve the validated dependency setup. Its historical Jackson
pin can break optional application startup. The required path uses Java methods through tests.
No instruction authorizes bypassing organizational artifact restrictions.
