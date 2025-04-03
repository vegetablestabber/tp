---
layout: page
title: T Hariidaran
parent: About Us
---

### Project: ModMate

ModMate is a command-line application that leverages the NUSMods API and was developed to help NUS students manage academic schedules and module information efficiently. It streamlines timetable organization, module bookmarking, and data exploration, offering a user-friendly solution for academic planning.

<img src="../img/harii/harii_tut.jpg" width="400">


Given below are my contributions to the project:

* **New Feature**: Enabled advanced module data handling for NUSMods integration.
  * **What it does**:
    * Developed the `Mod` class to represent comprehensive module details, including properties such as faculty, prerequisites, workload, and semester availability.
    * Developed the `CondensedMod` class to encapsulate essential module details, such as module code and title, for streamlined data representation.
    * Implemented the `ModAttributes` class to manage comprehensive module metadata, including faculty, prerequisites, workload, and semester availability.
  * **Justification**: Ensures smooth and efficient processing of module data, enabling features like module search and filtering to function effectively.
  * **Highlights**: Emphasized creating modular and scalable data structures to support future enhancements and maintainability.

* **New Feature**: Enabled the application to process and utilize JSON data from the NUSMods API.
  * **What it does**:
    * Converts raw JSON data from the NUSMods API into structured and type-safe objects, such as `ModJSONKey`, `TimetableJSONKey`, and `LessonJSONKey`, which represent key-value mappings for module, timetable, and lesson data respectively.
    * Implements specialized parsers like `ModJSONParser`, `TimetableJSONParser`, and `LessonJSONParser` to handle nested JSON structures and transform them into usable domain objects.
  * **Justification**: This capability is crucial for powering features like timetable generation and module exploration by ensuring accurate and reliable data handling.
  * **Highlights**: Required the development of a robust parsing mechanism to handle complex nested JSON structures.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=vegetablestabber&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=vegetablestabber&tabRepo=AY2425S2-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project Management**:
  * **Release Planning**: Led the planning and execution of v2.0 release, setting milestones, defining deliverables, and ensuring adherence to the timeline.
  * **Team Coordination**: Facilitated discussions, organized regular meetings, and addressed challenges to maintain progress and alignment.
  * **Outcome Focused**: Delivered v2.0 on schedule with high-quality standards, ensuring project success.

* **Enhancements to existing features**:
  * [#28](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/28), [#52](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/52), [#57](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/57): Refactored the download functionality to improve code readability and maintainability. This involved restructuring the logic to ensure better modularity and testability.
  * [#78](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/78): Enhanced logging mechanisms to provide more detailed and actionable runtime information, aiding in debugging and monitoring.
  * [#27](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/27): Reduced excessive null-checking by implementing more robust null-safety practices, leading to cleaner and more reliable code.

__*TODO: Update the contributions below*__

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_