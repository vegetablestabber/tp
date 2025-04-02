# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

## Features

### Adding a mod to a timetable: `addModToTimetable`
Adds a new module (`Mod`) to a specified timetable.

**Format**: `addModToTimetable TIMETABLE_NAME MOD_CODE_OR_NAME`

- The `TIMETABLE_NAME` is the name of the timetable to which the module is being added.
- The `MOD_CODE_OR_NAME` is the code or name of the module to add.
- The operation checks for the timetable and module's existence, adds the module to the timetable, and saves the updated state.

**Example Usage**:

`addModToTimetable "Fall 2025 Schedule" "CS1010"`

This will add the module `CS1010` to the timetable **"Fall 2025 Schedule"**.

---

### Removing a mod from a timetable: `removeModFromTimetable`
Removes a specified module (`Mod`) from a timetable.

**Format**: `removeModFromTimetable TIMETABLE_NAME MOD_CODE_OR_NAME`

- The `TIMETABLE_NAME` is the name of the timetable from which the module will be removed.
- The `MOD_CODE_OR_NAME` is the code or name of the module to remove.
- The operation checks for the timetable and module's existence, removes the module from the timetable, and saves the updated state.

**Example Usage**:

`removeModFromTimetable "Fall 2025 Schedule" "CS1010"`

This will remove the module `CS1010` from the timetable **"Fall 2025 Schedule"**.

---

### Detailed Design Explanation

#### a. **Method Design**

- **`addModToTimetable`**:
    - Loops through the `timatables` list to find a timetable by name (case-insensitive).
    - Calls `addMod` on the found timetable to add the `Mod` object.
    - If no timetable is found, an error message is printed.

- **`removeModFromTimetable`**:
    - Functions similarly to `addModToTimetable`, but calls `removeMod` instead of `addMod` on the timetable.

#### b. **Class Design**

- **Timetable Class**:
    - Contains a list or set of `Mod` objects.
    - Methods:
        - `addMod(Mod mod)`: Adds a module to the timetable.
        - `removeMod(Mod mod)`: Removes a module from the timetable.

- **Mod Class**:
    - Represents a module that can be added or removed from a timetable.
    - Attributes:
        - `ID`: A unique identifier for the module.
        - `name`: The name of the module.

#### c. **Alternatives Considered**

- **Optimization with a Map**:
    - Instead of searching through the entire list of `Timetable` objects every time, we could use a `Map<String, Timetable>`, where the key is the timetable name. This would speed up lookups to O(1) time complexity, providing faster access when managing timetables.

- **Error Handling**:
    - **Current Approach**: Instead of just printing an error, we could throw a custom exception (e.g., `TimetableNotFoundException`) when a timetable is not found. This would formalize error handling and make it easier to manage error propagation through the system.
    - **Pros**: Clear and formal error management, making it easier to debug and handle specific cases.
    - **Cons**: Adds complexity, especially if the exception handling needs to be propagated or caught higher in the stack.

### Command Summary

* `addModToTimetable TIMETABLE_NAME MOD_CODE_OR_NAME`: Adds a mod to a timetable.
* `removeModFromTimetable TIMETABLE_NAME MOD_CODE_OR_NAME`: Removes a mod from a timetable.
  ![SequenceDiagram](docs/team/img/image.png)


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
