---
layout: page
title: Sittinisaisuk Patiparn
parent: About Us
---

### Project: ModMate

ModMate is the ultimate companion for managing your modules and timetables at the National University of Singapore (NUS)!

Given below are my contributions to the project.

* **New Feature**: Added the ability to bookmark mods [(#23)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/23) that the user is interested in. Users can browse their bookmarks at any time, and bookmark any course available on ModMate. 
* **New Feature**: Added the ability to search for mods [(#65)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/65) using a keyword in the mod title, or the mod's code

* **New Feature**: Added the ability for users to select lecture, lab, and tutorial sessions for their timetables [(#84)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/84)
  * This brings ModMate on par with services like NUSMods, which allows users to create individualized timetables and account for potential scheduling conflicts.
  * Additionally, the timetable command has been customized to allow users to view their timetable sorted by course, or by day of the week. When sorted chronologically, the timetable is shown as below. This allows the user to browser their schedule and use the program as a reference to their weekly workload.
```
[MONDAY]
 | EE2026: [01] Weeks 1–13, MONDAY, 09:00-12:00 @ E4-03-06
 | EE2026: [01] Weeks 1–13, MONDAY, 17:00-18:00 @ LT6
[TUESDAY]
 | CG2023: [02] Weeks 1–13, TUESDAY, 09:00-12:00 @ E4-03-06
 | CG2023: [03] Weeks 1–13, TUESDAY, 16:00-18:00 @ EA-06-03
[WEDNESDAY]
 | CS2113: [5] Weeks 1–13, WEDNESDAY, 12:00-13:00 @ COM1-0210
[THURSDAY]
 | EE2026: [01] Weeks 1–13, THURSDAY, 09:00-11:00 @ LT6
 | EE2026: [08] Weeks 1–13, THURSDAY, 13:00-14:00 @ E3-06-02
 | EE2211: [25] Weeks 1–13, THURSDAY, 14:00-16:00 @ E3-06-06
 | CG2023: [03] Weeks 1–13, THURSDAY, 16:00-18:00 @ EA-06-03
[FRIDAY]
 | EE2211: [01] Weeks 1–13, FRIDAY, 12:00-14:00 @ E-Learn_A
 | CS2113: [1] Weeks 1–13, FRIDAY, 16:00-18:00 @ LT16
```

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=w12-1)

* **Enhancements to existing features**:
  * Add more descriptive error messages on invalid commands [(#62)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/62)
  * Add sorting to `viewallmods` to display them alphabetically [(#63)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/63)

* **Documentation**:
  * User Guide:
    * Added more examples for the features `timetable` and `setlesson`
  * Developer Guide:
    * Added implementation details of the timetabling feature, with the appropriate UML diagrams.

* **Community**:
  * Fix occasional bugs: [#70](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/70), [#71](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/71), [#73](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/73)