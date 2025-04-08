---
layout: page
title: Luke Williamson
parent: About Us
---

### Project: Modmate
{: .no_toc }

## Table of Contents
{: .no_toc .text-delta }

1. TOC
{:toc}

## Introduction
Modmate is a command-line application that helps keep track of your timetables and modules at NUS. It is a very simple way to keep yourself always on track with your classes at NUS.

---

## Contributions

Given below are my contributions to the project.

<iframe src="https://nus-cs2113-ay2425s2.github.io/tp-dashboard/#/widget/?search=W12-1&sort=groupTitle%20dsc&sortWithin=totalCommits%20dsc&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&optimiseTimeline=true&viewRepoTags=true&tabOpen=true&tabType=authorship&tabAuthor=gitHST&tabRepo=AY2425S2-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&chartGroupIndex=0&chartIndex=0" frameBorder="0" width="800px" height="169px"></iframe>

* **New Feature**: Designed and integrated the `HPPTUtil` and `NusModsAPI` classes
  * What it does: Enables efficient, modular retrieval and formatting of data from the NUSMods public API, encapsulating network logic and result parsing.
  * Highlights: Modular and testable design, allowing future extensions such as caching or additional endpoints.

## Project management:

  * Set up initial code templates and structure for commands and fetchers.
  * Wrote base logging utility (`Logger`) to ensure consistent and easily configurable log output across the application.

## Enhancements to existing features:

  * Refactored internal command structure for improved extensibility and separation of concerns.
  * Improved error feedback to users when invalid module codes or commands are entered.

## Documentation:

  * Developer Guide:
    * Documented the design and implementation of the `viewmod` command.
    * Included class diagrams and detailed descriptions of the `HTTPUtil` and `NusModsAPI` utility classes.

## Community:

  * PRs reviewed: [\#7](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/7),[\#10](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/10),[\#23](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/23), [\#27](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/27), [\#32](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/32), [\#52](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/52), [\#59](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/59), [\#74](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/74), [\#85](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/85), [\#97](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/97)

## Tools:

  * Setup and maintained Gradle-based build system and dependency management.
