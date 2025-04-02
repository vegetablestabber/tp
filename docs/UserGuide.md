# User Guide

## Introduction

🎉 Welcome to **ModMate**, your ultimate companion for managing your modules and timetables at the National University of Singapore (NUS)! 📚 ModMate is designed to help students efficiently organize their academic schedules, bookmark favorite modules, and explore detailed module information.

✨ With ModMate, you can create personalized timetables, add or remove modules, set lessons, and much more—all through a simple and intuitive command-line interface.

🚀 Start using ModMate today and take control of your academic schedule!

## Quick Start

> ⚠️ **Note for CS2113 instructors and peers**
>
> For detailed instructions on manual testing, understanding the codebase, and other essential development practices, please refer to the [Developer Guide](DeveloperGuide.md).
>
> 📘 The Developer Guide includes insights into design decisions, coding standards, and troubleshooting tips to help you gain a comprehensive understanding of the project.

Follow these steps to get started with ModMate:

1. ✅ Ensure that you have Java 17 or above installed on your computer.
2. 📥 Download the latest version of ModMate from [our releases page](https://github.com/AY2425S2-CS2113-W12-1/tp/releases).
3. 💻 Open a terminal or command prompt and navigate to the directory where you downloaded ModMate.
4. ▶️ Run the application using the command: `java -jar ModMate.jar`.
5. 🎮 Start exploring ModMate by entering commands. Type `-h` to view the list of available commands.

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

**Format:** `addmod <timetable_name> <module_code_or_name>`

- The `<module_code_or_name>` can be either the module code (e.g., CS1010) or the module name.

**Example:**
```
addmod Semester1 CS1010
```

### Removing a Module from a Timetable: `removemod`
➖ Remove a module from your timetable.

**Format:** `removemod <timetable_name> <module_code_or_name>`

**Example:**
```
removemod Semester1 CS1010
```

### Viewing a Timetable: `timetable`
👀 View the details of a specific timetable.

**Format:** `timetable <timetable_name>`

**Example:**
```
timetable Semester1
```

### Searching for Modules: `searchmod`
🔍 Search for modules by their code or name.

**Format:** `searchmod <search_query>`

**Example:**
```
searchmod Programming
```

### Bookmarking a Module: `bookmark`
⭐ Bookmark a module for quick access later.

**Format:** `bookmark <module_code_or_name>`

**Example:**
```
bookmark CS1010
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
```

### Adding a Break to a Timetable: `addbreak`
☕ Add a break period to your timetable.

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

## FAQ

**Q**: How do I transfer my data to another computer?
**A**: 📂 Copy the `data` folder from your current ModMate directory to the new computer. Ensure that the folder structure remains intact.

**Q**: What should I do if I encounter an error?
**A**: ⚙️ Check the error message displayed in the terminal. If the issue persists, refer to the logs in the `logs/log.txt` file for more details.

**Q**: Can I use ModMate without an internet connection?
**A**: 🌐 Yes, most features of ModMate work offline. However, fetching module details from the NUSMods API requires an active internet connection.

## Command Summary

Here’s a quick reference for all ModMate commands:

| Command                     | Description                                      |
|-----------------------------|--------------------------------------------------|
| `createtimetable`           | 🗓️ Create a new timetable.                      |
| `addmod`                    | ➕ Add a module to a timetable.                  |
| `removemod`                 | ➖ Remove a module from a timetable.             |
| `timetable`                 | 👀 View the details of a timetable.             |
| `searchmod`                 | 🔍 Search for modules by code or name.          |
| `bookmark`                  | ⭐ Bookmark a module.                            |
| `bookmarks`                 | 📑 View all bookmarked modules.                 |
| `setlesson`                 | 📚 Set a specific lesson for a module.          |
| `addbreak`                  | ☕ Add a break period to a timetable.            |
| `viewmod`                   | 📖 View detailed information about a module.    |
| `viewlessons`               | 📝 View all available lessons for a module.     |