---
title: User Guide
layout: default
nav_order: 2
nav_enabled: true
---

# User Guide
{: .no_toc }

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

## Command Summary

Here’s a quick reference for all ModMate commands:

| Command           | Description                                    |
|-------------------|------------------------------------------------|
| `createtimetable` | 🗓️ Create a new timetable.                    |
| `addmod`          | ➕ Add a module to a timetable.                 |
| `removemod`       | ➖ Remove a module from a timetable.            |
| `timetable`       | 👀 View the details of a timetable.            |
| `searchmod`       | 🔍 Search for modules by code or name.         |
| `bookmark`        | ⭐ Bookmark a module.                           |
| `bookmarks`       | 📑 View all bookmarked modules.                |
| `setlesson`       | 📚 Set a specific lesson for a module.         |
| `addbreak`        | ☕ Add a break period to a timetable.           |
| `viewmod`         | 📖 View detailed information about a module.   |
| `viewlessons`     | 📝 View all available lessons for a module.    |
| `viewallmods`     | 📝 View all available modules in the database. |

## Features

ModMate offers a variety of features to help you manage your academic life effectively:

### Creating a Timetable: `createtimetable`

🗓️ Create a new timetable to organize your modules.

**Format:** `createtimetable <timetable_name>`

- The `<timetable_name>` must be unique.
- Timetable names are case-insensitive.

**Example:**
```
createtimetable Semester1
```

### Adding a Module to a Timetable: `addmod`

➕ Add a module to your timetable.

- This command will assign random lessons for each type (lectures, labs, and/or tutorials). 
You can customize which slots to take with the `setlesson` command.

**Format:** `addmod <timetable_name> <module_code_or_name>`

- The `<module_code_or_name>` can be either the module code (e.g., CS1010) or the module name.

**Example:**
```
addmod Semester1 CS1010
addmod Semester1 Programming Methodology
```

### Removing a Module from a Timetable: `removemod`

➖ Remove a module from your timetable.

**Format:** `removemod <timetable_name> <module_code_or_name>`

**Example:**
```
removemod Semester1 CS1010
removemod Semester1 Programming Methodology
```

### Viewing a Timetable: `timetable`

👀 View the details of a specific timetable.

- Adding the argument "timeline" will display your timetable in chronological order as one weekly schedule.

**Format:** `timetable [timeline] <timetable_name>`

**Example:**
```
timetable Semester1
timetable timeline Semester2
```

### Searching for Modules: `searchmod`

🔍 Search for modules by their code or name.

**Format:** `searchmod <search_query>`

**Example:**
```
searchmod Programming
searchmod GESS
```

### Bookmarking a Module: `bookmark`

⭐ Bookmark a module for quick access later.

**Format:** `bookmark <module_code_or_name>`

**Example:**
```
bookmark CS1010
bookmark CG2023
```

### Viewing Bookmarked Modules: `bookmarks`

📑 View all your bookmarked modules.

**Format:** `bookmarks`

**Example:**
```
bookmarks
```

### Setting a Lesson for a Module: `setlesson`

📚 Set a specific lesson for a module in your timetable.

**Format:** `setlesson <timetable_name> <module_code_or_name> <lesson_type> <lesson_id>`

**Example:**
```
setlesson Semester1 CS1010 Lecture L1
setlesson Semester2 LAJ3201 TutorialType2 B1
setlesson SpecialSem1 EE2026 Laboratory 01
```

### Adding a Break to a Timetable: `addbreak`

☕ Add a break period to your timetable. Note that the timing should be in 24-hour clock.

**Format:** `addbreak <timetable_name> <label> <day> <start_time> <end_time>`

**Example:**
```
addbreak Semester1 LunchBreak Monday 12:00 13:00
```

### Viewing Module Details: `viewmod`

📖 View detailed information about a specific module.

**Format:** `viewmod <module_code_or_name>`

**Example:**
```
viewmod CS1010
```

### Viewing Lessons for a Module: `viewlessons`

📝 View all available lessons for a specific module.

**Format:** `viewlessons <module_code_or_name>`

**Example:**
```
viewlessons CS1010
```

### Viewing all Modules (name + code): `viewallmods`

📝 View all available modules.

**Format:** `viewallmods`

**Example:**
```
viewallmods
```

## FAQ

- **Q**: How do I transfer my data to another computer?

    **A**: 📂 Copy the `data` folder from your current ModMate directory to the new computer. Ensure that the folder structure remains intact.

- **Q**: What should I do if I encounter an error?

    **A**: ⚙️ Check the error message displayed in the terminal. If the issue persists, refer to the logs in the `logs/log.txt` file for more details.

- **Q**: Can I use ModMate without an internet connection?

    **A**: 🌐 Yes, most features of ModMate work offline. However, fetching module details from the NUSMods API requires an active internet connection.