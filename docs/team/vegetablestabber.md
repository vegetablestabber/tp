---
layout: page
title: T Hariidaran
parent: About Us
---

### Project: ModMate
{: .no_toc }

## Table of Contents
{: .no_toc .text-delta }

1. TOC
{:toc}

## Introduction

ModMate is a command-line application that leverages the NUSMods API and was developed to help NUS students manage academic schedules and module information efficiently. It streamlines timetable organization, module bookmarking, and data exploration, offering a user-friendly solution for academic planning.

<div style="text-align: center;">
  <img src="../img/harii/harii_tut.jpg" width="400" alt="Learning about UML diagrams during a tutorial">
  <p><em>Learning about UML diagrams during a tutorial</em></p>
</div>

---

## Code Contributed

<iframe src="https://nus-cs2113-ay2425s2.github.io/tp-dashboard/#/widget/?search=W12-1&sort=groupTitle%20dsc&sortWithin=totalCommits%20dsc&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&optimiseTimeline=true&viewRepoTags=true&tabOpen=true&tabType=authorship&tabAuthor=vegetablestabber&tabRepo=AY2425S2-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&chartGroupIndex=0&chartIndex=0" frameBorder="0" width="800px" height="169px"></iframe>

---
## Project Management

- Set internal deadlines to ensure timely progress and maintain alignment with the overall project timeline.
- Delegated tasks effectively to leverage team strengths and maximize productivity.
- Regularly reminded teammates of pending tasks to maintain momentum and avoid delays.
- Reviewed teammates' PRs thoroughly, providing constructive feedback to improve code quality and functionality.
- Fixed numerous bugs to enhance application stability and user experience.
- Applied code quality principles like SLAP to ensure maintainable and readable code.
- Refactored code extensively to improve modularity and adhere to best practices.
- Encouraged team collaboration and adherence to high standards for a successful project outcome.

## Features

### Commands

| Feature                  | Description                                                                                     | Justification                                                                            | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| `SearchModCommand` and Supporting Classes | Enables users to search for modules by code or name with filters for faculty, semesters, etc., supported by utilities like `SearchUtil` and `SearchFlagBuilder`. | Provides efficient module search functionality with reusable utilities and flag handling. | [#88](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/88), [#186](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/186), [#180](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/180) |

### Networking

| Feature                  | Description                                                                                     | Justification                                                                            | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| JSON Parsing             | Implemented `JSONParser`, `JSONKey`, and specialized parsers (`ModJSONParser`, etc.) for handling API responses. | Ensures accurate and type-safe processing of JSON data from the NUSMods API.            | [#57](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/57), [#52](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/52) |
| HTTP Utilities           | Developed `HttpUtil` for handling HTTP requests and responses.                                 | Simplifies and standardizes HTTP communication with external APIs.                      | [#28](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/28)                               |

### General

| Feature                  | Description                                                                                     | Justification                                                                            | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Input Handling           | Implemented `InputParser`, `Input`, `Argument`, and `Flag` for structured input parsing.        | Improves input validation and parsing for better user experience and error handling.    | [#185](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/185), [#186](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/186) |
| Pagination               | Added `Pagination` to improve user experience for commands like `SearchModCommand`.            | Allows users to view large sets of data in manageable chunks.                           | [#88](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/88)                               |
| Command Parsing          | Developed `CommandParser` to streamline command execution.                                     | Centralizes command handling logic for better maintainability and scalability.          | [#186](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/186)                             |

---

## Documentation

### General

| Contribution             | Description                                                                                     | Justification                                                                            | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Initial Structure        | Designed the skeleton structure and implemented most of the documentation for the User Guide and Developer Guide. | Provides a strong foundation for comprehensive and organized project documentation.     | [#50](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/50)                               |
| Jekyll Integration       | Integrated Jekyll and the "Just the Docs" theme for project documentation.                      | Simplifies navigation and improves readability of the documentation.                    | [#92](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/92)                               |

### User Guide

| Contribution             | Description                                                                                     | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Search Module            | Added documentation for the `searchmod` feature.                                               | [#88](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/88)                               |
| Cosmetic Tweaks          | Improved clarity and formatting for `clear` and `exit` commands.                               | [#74](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/74)                               |

### Developer Guide

| Contribution             | Description                                                                                     | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Search Module            | Added implementation details and diagrams for the `searchmod` feature.                         | [#88](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/88)                               |

---

## Community

| Contribution             | Description                                                                                     | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| PR Reviews               | Reviewed PRs with non-trivial comments.                                                        | [#12](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/12), [#32](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/32) |
| Bug Reports              | Reported bugs and suggestions for other teams.                                                 | [#169](https://github.com/AY2425S2-CS2113-W12-1/tp/issues/169), [#158](https://github.com/AY2425S2-CS2113-W12-1/tp/issues/158), [#146](https://github.com/AY2425S2-CS2113-W12-1/tp/issues/146), [#145](https://github.com/AY2425S2-CS2113-W12-1/tp/issues/145) |

---

## Tools

| Tool                     | Description                                                                                     | Links                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Jekyll                   | Used for generating static documentation sites.                                                | [#92](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/92)                               |
| Just the Docs            | Integrated as the theme for the documentation.                                                 | [#92](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/92)                               |
