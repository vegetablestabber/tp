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
  * Additionally, the timetable command has been customized to allow users to view their timetable sorted by course, or by day of the week. When sorted chronologically, the timetable is shown as below. This allows the user to browser their schedule and use the program as a reference to their weekly workload. Clashes are also shown when two lessons overlap.
```
[MONDAY]
 ╳ CG2023 Laboratory: 14:00-17:00
 ╳ LAJ3202 Tutorial: 14:00-16:00
 │ EE2026 Lecture: 17:00-18:00
[TUESDAY]
 │ CG2023 Lecture: 16:00-18:00
[WEDNESDAY]
 │ EE2026 Laboratory: 14:00-17:00
[THURSDAY]
 │ EE2026 Lecture: 09:00-11:00
 │ CS2113 Tutorial: 11:00-12:00
 │ EE2026 Tutorial: 13:00-14:00
 │ EE2211 Tutorial: 14:00-16:00
 ╳ CG2023 Lecture: 16:00-18:00
 ╳ LAJ3202 Tutorial Type 2: 16:00-18:00
[FRIDAY]
 │ EE2211 Lecture: 12:00-14:00
 │ CS2113 Lecture: 16:00-18:00
 │ LAJ3202 Lecture: 18:00-20:00
```

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=w12-1)

* **Enhancements to existing features**:
  * Add more descriptive error messages on invalid commands [(#62)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/62)
  * Add sorting to `viewallmods` to display them alphabetically [(#63)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/63)
  * Updating both of my features from using the old command parsing systems to the new InputParser class [(#193)](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/193)

* **Documentation**:
  * User Guide:
    * Added more examples for the features `timetable` and `setlesson`
  * Developer Guide:
    * Added implementation details of the timetabling feature, with the appropriate UML diagrams.

* **Community**:
  * Fix occasional bugs: 
    * [#70](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/70) Fixing addmod / removemod parsing
    * [#71](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/71) Remove case sensitivity for mod code/names
    * [#73](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/73) Allow for mod workload to be null / unavailable
    * [#182](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/182) / [#183](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/183) Fixing bugs related to bookmarks/scheduling
