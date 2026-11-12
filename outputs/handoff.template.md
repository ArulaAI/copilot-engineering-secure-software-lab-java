---
stage: handoff
last_stage: ""            # 1a-map | 1b-analyze | 2-threat-model | 3-fix | 4-review
---

# Hand-off log

Each stage's skill appends one section when it writes its report. The next stage reads this file first.
Each section points to files rather than repeating them, and stays under 12 lines.

<!-- Section format, repeated per stage:

## Stage N hand-off: <stage name>
- Decided: <decisions the learner made or accepted>
- Evidence: <outputs files written this stage, and commands run with their summary lines>
- Gate: <pass | fail, with the unchecked item if fail>
- Open questions: <unknowns, marked unknown rather than guessed>
- Next stage needs: <the minimum the next stage must know or preserve>

-->
