---
title: User Guide
layout: default
nav_order: 2
nav_enabled: true
---

# User Guide
{: .no_toc }

{% include intro.md %}

## Table of Contents
{: .no_toc .text-delta }

1. TOC
{:toc}

## Quick Start

{: .note-to-reader }
For detailed instructions on manual testing, understanding the codebase, and other essential development practices, please refer to the [Developer Guide](DeveloperGuide.md).

Follow these steps to get started with ModMate:

1. ✅ Ensure that you have Java 17 or above installed on your computer.
2. 📥 Download the latest version of ModMate from [our releases page](https://github.com/AY2425S2-CS2113-W12-1/tp/releases).
3. 💻 Open a terminal or command prompt and navigate to the directory where you downloaded ModMate.
4. ▶️ Run the application using the command: `java -jar ModMate.jar`.
5. 🎮 Start exploring ModMate by entering commands. Type `-h` to view the list of available commands.

## Limitations

- Modmate only allows you to add modules from Semester 2
- Modmate does not accurately show individual weeks for each lesson

## Command Summary

Here’s a quick reference for all ModMate commands:

| Command           | Description                                      |
|-------------------|--------------------------------------------------|
| `help`            | ❓ Display help information for commands.         |
| `createtimetable` | 🗓️ Create a new timetable.                      |
| `addmod`          | ➕ Add a module to a timetable.                   |
| `removemod`       | ➖ Remove a module from a timetable.              |
| `timetable`       | 👀 View the details of a timetable.              |
| `searchmod`       | 🔍 Search for modules by code, name, or filters. |
| `bookmark`        | ⭐ Bookmark a module.                             |
| `bookmarks`       | 📑 View all bookmarked modules.                  |
| `setlesson`       | 📚 Set a specific lesson for a module.           |
| `addbreak`        | ☕ Add a break period to a timetable.             |
| `viewmod`         | 📖 View detailed information about a module.     |
| `viewlessons`     | 📝 View all available lessons for a module.      |
| `viewallmods`     | 📝 View all available modules in the database.   |
| `exit`            | 📝 Exit the program.                             |

## Features

ModMate offers a variety of features to help you manage your academic life effectively:

### Displaying Help: `help`

❓ Display help information for all commands or a specific command.

**Format:** `help [command]`

- If no command is specified, a list of all commands and their descriptions will be displayed.
- If a command is specified, detailed usage information for that command will be displayed. (only for searchmod)

**Example:**
```
help
Commands:
help: Display this help message
exit: Exit the application
viewmod <mod code or name>: View details of a mod by its mod code or name
viewlessons <mod code or name>: View available lessons for each mod, sorted by type
setlesson <timetable> <mod code or name> <lesson type> <lesson id>: Set lesson for each mod, by type
searchmod <mod code or name>: Search for a mod by its code or name
bookmark <mod code or name>: Bookmark a mod for later reference
bookmarks: View all bookmarked mods
addmod <timetable> <mod code or name>: Add a mod to your list
removemod <timetable> <mod code or name>: Remove a mod from your list
createtimetable <timetable>: Create a new timetable
addbreak <timetable> <label> <day> <startTime> <endTime>: Add a break
timetable <timetable>: Display your mod timetable
viewallmods: View all available mods
```

```
help searchmod
Description: Search for a mod by its code or name.
Usage: searchmod <search query> --faculty <value> --semesters <value> --units <value> --graded <value>
Arguments:
  <search query>: Optional. Mod code or name.
  --faculty: Optional. Filter by faculty.
  --semesters: Optional. Filter by semesters (space-separated).
  --units: Optional. Filter by modular credits.
  --graded: Optional. Filter by grading type.
```

### Creating a Timetable: `createtimetable`

🗓️ Create a new timetable to organize your modules.

**Format:** `createtimetable <timetable_name>`

- The `<timetable_name>` must be unique.
- Timetable names are case-insensitive.
- Timetable names can only be one word (no spaces in between)
  - `timetable Semester1` is okay
  - `timetable Semester 1` is not okay

**Example:**
```
createtimetable Semester1
Timetable 'Semester1' created successfully.
```

### Adding a Module to a Timetable: `addmod`

➕ Add a module to your timetable.

- This command will assign random lessons for each type (lectures, labs, and/or tutorials).
You can customize which slots to take with the `setlesson` command.
- **WARNING!** Be careful when adding modules with names similar to other modules. You are advised to use module code to add instead.

**Format:** `addmod <timetable_name> <module_code_or_name>`

- The `<module_code_or_name>` can be either the module code (e.g., CS1010) or the exact module name (case-insensitive).

**Example:**
```
addmod Semester1 CS1010
Mod CS1010 added successfully to Semester1.
```

```
addmod Semester1 Software Engineering & Object-Oriented Programming
Mod CS2113 added successfully to Semester1.
```

### Removing a Module from a Timetable: `removemod`

➖ Remove a module from your timetable.

- **WARNING!** Be careful when removing modules with names similar to other modules. You are advised to use module code to add instead.

**Format:** `removemod <timetable_name> <module_code_or_name>`

**Example:**
```
removemod Semester1 CS1010
Mod CS1010 removed successfully from Semester1.
```
```
removemod Semester1 Software Engineering & Object-Oriented Programming
Mod CS2113 removed successfully from Semester1.
```

### Viewing a Timetable: `timetable`

👀 View the details of a specific timetable.

- Adding the argument "list" will display your timetable as a list
- By default, your timetable will be displayed in chronological order as one weekly schedule.

`X` denotes lessons that overlap with another lesson in your timetable.
`|` denotes lessons that do not overlap with another lesson in your timetable.

**Format:** `timetable [line] <timetable_name>`

**Example:**
```
timetable Semester1
[MONDAY]
 │ CS1010 Tutorial: 10:00-12:00
[TUESDAY]
 │ CS1010 Sectional Teaching: 10:00-12:00
[WEDNESDAY]
[THURSDAY]
[FRIDAY]
```
```
timetable list Semester2
Timetable: Semester2
  CS1010: Programming Methodology
  
      Tutorial: [[01] Weeks 1–13, MONDAY, 10:00-12:00 @ COM1-B108]
      Sectional Teaching: [[1] Weeks 1–13, TUESDAY, 10:00-12:00 @ COM4-02-01]
```


### Searching for Modules: `searchmod`

🔍 Search for modules from all available modules provided by NUSMods by their code, name, or using filters.

**Format:** `searchmod [search_query] [--faculty FACULTY] [--semesters SEMESTERS] [--units UNITS] [--graded true|false]`

- `[search_query]`: Search by module code or name (e.g., "CS1010" or "Programming").
- `--faculty`: Filter by faculty (e.g., "Computing").
- `--semesters`: Filter by semesters (space-separated, e.g., "1 2").
- `--units`: Filter by modular credits (e.g., "4").
- `--graded`: Filter by grading type (true for graded, false for ungraded).

**Notes:**
- You can use either the search query or filters, or combine them for more specific results.
- At least one argument or filter must be provided.

**Examples:**
```
searchmod CS1010
searchmod Programming
searchmod --faculty Computing
searchmod --semesters 1 2
searchmod --units 4
searchmod --graded true
searchmod CS1010 --faculty Computing -units 4
```

**Output:**
- Displays a list of matching modules with their codes and names.
- If no matches are found, a message will indicate that no modules were found.

### Bookmarking a Module: `bookmark`

⭐ Bookmark a module for quick access later.

**Format:** `bookmark <module_code_or_name>`

**Example:**
```
bookmark CS1010
Bookmark CS1010 successfully added to your list.
```
```
bookmark cg2023
Bookmark cg2023 successfully added to your list.
```

### Viewing Bookmarked Modules: `bookmarks`

📑 View all your bookmarked modules.

**Format:** `bookmarks`

**Example:**
```
bookmarks
You have 2 bookmark(s).
CS1010: Programming Methodology
CG2023: Signals and Systems
```

### Setting a Lesson for a Module: `setlesson`

📚 Set a specific lesson for a module in your timetable.

-**WARNING!** Lesson type has to be exact, with spaces removed.
-**WARNING!** Lesson id has to be exact. Take note of leading zeros e.g. 01

**Format:** `setlesson <timetable_name> <module_code_or_name> <lesson_type> <lesson_id>`

**Example:**
```
setlesson SpecialSem1 EE2026 Laboratory 01
Laboratory 01 for EE2026 added.
```

```
setlesson Semester2 LAJ3201 TutorialType2 B1
Tutorial Type 2 B1 for LAJ3201 added.
```

```
setlesson Semester1 CS1010 Lecture L1
Lesson type 'lecture' does not exist.
```

### Adding a Break to a Timetable: `addbreak`

☕ Add a break period to your timetable. Note that the timing should be in 24-hour clock.

**Format:** `addbreak <timetable_name> <label> <day> <start_time> <end_time>`

**Example:**
```
addbreak Semester1 LunchBreak Monday 12:00 13:00
Break [lunchbreak] added to timetable 'semester1'.
```

```
addbreak semester1 lunch tuesday 1200 1400
Break [lunch] added to timetable 'semester1'.
```

### Viewing Module Details: `viewmod`

📖 View detailed information about a specific module.

**Format:** `viewmod <module_code_or_name>`

**Example:**
```
viewmod CS1010
Code: CS1010
Name: Programming Methodology
Description: This course introduces the fundamental concepts of problem solving by computing and programming using an imperative programming language. It is the first and foremost introductory course to computing. Topics covered include computational thinking and computational problem solving, designing and specifying an algorithm, basic problem formulation and problem solving approaches, program development, coding, testing and debugging, fundamental programming constructs (variables, types, expressions, assignments, functions, control structures, etc.), fundamental data structures (arrays, strings, composite data types), basic sorting, and recursion.
Faculty: Computing
Availability: Semester 1, Semester 2
Units: 4.0
Graded: true
Workload: Lectures: 2.0 hours, Tutorials: 1.0 hours, Projects: 3.0 hours, Preparation: 3.0 hours
```

### Viewing Lessons for a Module: `viewlessons`

📝 View all available lessons for a specific module.

**Format:** `viewlessons <module_code_or_name>`

**Example:**
```
viewlessons CS1010
[Tutorial]
    [01] Weeks 1–13, MONDAY, 10:00-12:00 @ COM1-B108
    [02] Weeks 1–13, MONDAY, 10:00-12:00 @ COM1-B111
    [03] Weeks 1–13, MONDAY, 10:00-12:00 @ COM1-B110
    [04] Weeks 1–13, MONDAY, 12:00-14:00 @ COM1-B108
    [05] Weeks 1–13, MONDAY, 12:00-14:00 @ COM1-B111
    [06] Weeks 1–13, MONDAY, 12:00-14:00 @ COM1-B110
[Sectional Teaching]
    [1] Weeks 1–13, TUESDAY, 10:00-12:00 @ COM4-02-01
```

### Viewing all Modules (name + code): `viewallmods`

📝 View all available modules.

**Format:** `viewallmods`

**Example:**
```
viewallmods
```

### Exit Program: `exit`

📝 Exit to quit the program.

**Format:** `exit`

**Example:**
```
exit
Exiting...

```

## FAQ

- **Q**: How do I transfer my data to another computer?

    **A**: 📂 Copy the `data` folder from your current ModMate directory to the new computer. Ensure that the folder structure remains intact.

- **Q**: What should I do if I encounter an error?

    **A**: ⚙️ Check the error message displayed in the terminal. If the issue persists, refer to the logs in the `logs/log.txt` file for more details.

- **Q**: Can I use ModMate without an internet connection?

    **A**: 🌐 Yes, most features of ModMate work offline. However, fetching module details from the NUSMods API requires an active internet connection.
