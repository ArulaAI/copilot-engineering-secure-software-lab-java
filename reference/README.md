# Facilitator resources

These resources are outside src/ and do not compile into the training application.
Do not consult them during participant discovery, planning, implementation, or review.
They are visible in the public repository, so this is an educational boundary only.

## Reviewed solutions

`solutions/UserRepository.java` (SQLI-01) and `solutions/FileHandler.java` (PATH-01) change only
the assigned methods; the variants stay vulnerable. In a disposable checkout copy both into
`src/main/java/com/securelabs/vulnerable/data/` and run `mvn -Dsecurity.verify=true clean test`.
Ten tests must pass with zero skipped. Restore the originals before distributing the exercise.

`finding-catalog.md` lists the codebase's root causes for assessing Stages 1B and 2.
`threat-model.md` gives an example architecture, top 5 and expected red-team outcomes.

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
