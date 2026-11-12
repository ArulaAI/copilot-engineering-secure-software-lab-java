# Execution evidence

Maven and git write the lab's run evidence here, and every agent reads it by path. Nobody pastes
logs into chat. Keep this folder: Maven writes no log at all when it is missing.

| File | Stage | Written by |
| --- | --- | --- |
| baseline.txt | Setup | You |
| reproduce.txt | 1 Investigate | You |
| acceptance.txt | 3 Fix (rerun in 4) | Secure Remediation Engineer, then the reviewer |
| fix.diff | 3 Fix | Secure Remediation Engineer |
| redteam.txt | 4 Red-team | Secure Remediation Engineer |
| forms.txt | Any time | You, to check report headers |

The exact commands are in [docs/SCENARIO.md](../../docs/SCENARIO.md). With `-l`, Maven prints
nothing to the terminal; open the file to read the result.
