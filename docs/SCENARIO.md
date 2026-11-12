# Lab scenario

## The codebase

You have joined a team that owns a legacy Java Spring service. Nobody who wrote it is still
around. It has user accounts, payments, file uploads, sessions and an admin API, spread across
four packages under `src/main/java/com/securelabs/vulnerable/` (`api`, `auth`, `data`,
`session`), about 2,400 lines. All data is synthetic. Nothing is deployed.

Your job in this lab: understand the codebase, find and rank its security problems, decide what
to mitigate first, then fix two of them with evidence.

## Stages

1. **Map** the codebase (index, entry points, architecture diagram), then **analyze** it and
   rank findings by severity.
2. **Threat-model** the findings with STRIDE and blast radius, and choose the top 5 to mitigate.
3. **Fix** the two lab fixes below, proven by shipped exploit tests.
4. **Verify** the fixes in an isolated review, then **red-team** them.

## Lab fixes (assigned)

Every learner fixes these two, whatever their own ranking says. Both are one file each.

| Fix ID | File | Methods to change | Defect | A correct fix |
| --- | --- | --- | --- | --- |
| SQLI-01 | `vulnerable/data/UserRepository.java` | `findByEmail`, `searchUsers` | SQL built by string concatenation | Bind values as parameters; allow-list the sort column and throw `IllegalArgumentException` for anything else |
| PATH-01 | `vulnerable/data/FileHandler.java` | `readFile`, `deleteFile`, `listFiles` | User input joined to a folder path | Reject any path that resolves outside the upload folder with `IllegalArgumentException` or `SecurityException`, before touching the file system |

The same patterns appear in other methods and classes. Those are variants: record them in your
findings and threat model, but do not fix them in this lab.

Shipped tests in `src/test/java/com/securelabs/vulnerable/data/`:

- `UserRepositoryTest`, `FileHandlerTest`: legitimate behavior. They pass before and after the fix.
- `UserRepositorySecurityTest`, `FileHandlerSecurityTest`: exploit tests. They fail on the
  unfixed code and must pass after it. They run only with `-Dsecurity.verify=true`.

Never change these tests or `pom.xml`. In Stage 4 you may add one new test class ending in
`RedTeamCheck` in the same folder.

## Commands

| Purpose | Command |
| --- | --- |
| Baseline (setup) | `mvn -l outputs/evidence/baseline.txt clean test` |
| Reproduce | `mvn -l outputs/evidence/reproduce.txt -Dsecurity.verify=true "-Dtest=*SecurityTest" test` |
| Acceptance | `mvn -l outputs/evidence/acceptance.txt -Dsecurity.verify=true clean test` |
| Diff | `git diff --output=outputs/evidence/fix.diff -- src/main/java` |
| Red-team | `mvn -l outputs/evidence/redteam.txt "-Dtest=*RedTeamCheck" test` |
| Check report forms | `mvn -l outputs/evidence/forms.txt -Dtest=OutputsCheck test` |

Expected results: baseline 10 run, 0 failed, 6 skipped. Reproduce 6 run, 6 failed.
Acceptance 10 run, 0 failed, 0 skipped. With `-l`, Maven prints nothing to the terminal; open
the file to read the result.

## Out of scope

Fixing anything other than SQLI-01 and PATH-01, starting the Spring application, the
dependency CVEs in `pom.xml` (record them as findings only), and real deployment concerns such as
TLS, network controls and production identity. Symbolic links inside the upload folder are not
tested by this fixture; record them as residual risk if red-team raises them.
