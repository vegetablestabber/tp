# Developer Guide

## **Acknowledgements**

* The application integrates with the [NUSMods API](https://nusmods.com/) to fetch module data. Special thanks to the NUSMods team for providing their publicly available API.
* Command parsing logic is inspired by [AddressBook-Level3](https://github.com/se-edu/addressbook-level3).
* UML diagrams were created using [PlantUML](https://plantuml.com/).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### **Architecture**

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** above provides a high-level overview of the system. It shows the main components of the application and how they interact with each other.

**Main components of the architecture:**
1. **`Main`**: Handles application startup and shutdown. It initializes and connects the other components.
2. **`Command`**: Encapsulates user actions such as adding modules, searching for modules, or viewing timetables.
3. **`CommandLine`**: Parses user input and maps it to specific commands.
4. **`Model`**: Manages the application's data, including timetables and modules.
5. **`Storage`**: Handles reading and writing data to the local file system.
6. **`Download`**: Manages interactions with external APIs, such as fetching module data from NUSMods.
7. **`LogUtil`**: Provides centralized logging functionality for debugging and tracking application behavior.

### **How Components Interact**

1. **User Input**: The user enters a command via the CLI.
2. **Command Parsing**: The `CommandLine` component parses the input and creates the appropriate `Command` object.
3. **Command Execution**: The `Command` interacts with other components to perform the requested operation.
4. **API Interaction**: If module data is required, the `Command` invokes `NUSModsAPI`, which uses `HttpUtil` to fetch data from the NUSMods API and processes it using `JsonParser`.
5. **Data Update**: The application's state is updated based on the command's execution.
6. **Output Display**: The results of the operation are displayed to the user.

This design ensures a clear separation of concerns, making the application modular and easier to maintain.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### **Key Features**

#### **Command Parsing**
- **Purpose**: Maps user input to specific commands.
- **Implementation**: The `CommandLine` class uses a factory-like approach to instantiate the appropriate `Command` object based on the input.
- **Rationale**: This design allows for easy addition of new commands without modifying existing code.

#### **Timetable Management**
- **Purpose**: Allows users to create, update, and view academic schedules.
- **Implementation**: The `Timetable` class provides methods to add, remove, and view modules. The `TimetableManager` handles higher-level operations such as exporting timetables.
- **Rationale**: Encapsulating timetable logic in a dedicated class improves maintainability and testability.

#### **API Integration**
- **Purpose**: Fetches module data from the NUSMods API.
- **Implementation**: The `NUSModsAPI` class interacts with the API using `HttpUtil` for network requests and `JsonParser` for processing responses.
- **Rationale**: Separating API logic into a dedicated class ensures that external dependencies are isolated from core application logic.

#### **Logging**
- **Purpose**: Tracks application behavior for debugging and auditing.
- **Implementation**: The `LogUtil` class provides methods for logging messages at various levels (e.g., info, warning, severe).
- **Rationale**: Centralized logging ensures consistency and simplifies debugging.

--------------------------------------------------------------------------------------------------------------------

## **Product Scope**

### **Target User Profile**

* NUS students who need a tool to manage their academic modules and timetables.
* Users comfortable with Command Line Interfaces (CLI).

### **Value Proposition**

* Provides a lightweight and efficient way for NUS students to plan and manage their academic schedules and module information, tailored to the unique requirements of the NUS modular system.

--------------------------------------------------------------------------------------------------------------------

## **User Stories**

| Priority | As a …​          | I want to …​                     | So that I can …​                          |
| -------- | ---------------- | -------------------------------- | ----------------------------------------- |
| `* * *`  | new user         | see usage instructions           | refer to them when I forget how to use the application |
| `* * *`  | student          | search for a module by name or code | quickly find information about a specific module |
| `* *`    | student          | add a module to my timetable     | keep track of my academic schedule        |
| `* *`    | student          | view all modules in my timetable | get an overview of my academic workload   |
| `*`      | advanced user    | export my timetable to a file    | share it with others or back it up        |

--------------------------------------------------------------------------------------------------------------------

## **Non-Functional Requirements**

1. The application should work on any mainstream OS with Java 11 installed.
2. The response time for any command should not exceed 1 second.
3. The application should be able to handle up to 1000 modules without significant performance degradation.
4. The application should log all user actions and errors to a file for debugging purposes.

--------------------------------------------------------------------------------------------------------------------

## **Glossary**

* **Module** - A course or subject offered by the university.
* **Timetable** - A schedule of modules organized by semester.
* **CLI** - Command Line Interface, a text-based user interface.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

### **Loading Sample Data**
1. Create a `timetable.txt` file in the same directory as the application.
2. Ensure the file contains valid module data in the following format:
    ```
    MODULE_CODE|MODULE_NAME|SEMESTER
    ```
    Example:
    ```
    CS2113|Software Engineering & Object-Oriented Programming|2
    CS2101|Effective Communication for Computing Professionals|2
    ```

### **Testing Commands**
1. Launch the application by running the main class.
2. Test the following scenarios:
    - **Adding Modules**: Enter a valid `add` command (e.g., `add CS2113`) and verify the module is added to the timetable.
    - **Viewing Timetable**: Use the `view` command to display all added modules and confirm the output matches the expected timetable.
    - **Removing Modules**: Enter a valid `remove` command (e.g., `remove CS2113`) and ensure the module is removed from the timetable.
    - **Searching Modules**: Test the `search` command with valid and invalid module codes or names.

### **Error Handling**
1. Enter invalid commands (e.g., `add123`) and confirm that the application displays appropriate error messages.
2. Test edge cases:
    - Empty input: Press Enter without typing any command.
    - Invalid module codes: Use non-existent or malformed module codes (e.g., `add XYZ123`).
    - Excessively long descriptions: Enter commands with overly long inputs and verify the application handles them gracefully.

### **API Integration**
1. Ensure the application can fetch module data from the NUSMods API:
    - Disconnect from the internet and attempt to fetch data to verify error handling.
    - Reconnect and confirm that valid data is retrieved and displayed correctly.

### **File Operations**
1. Test saving and loading functionality:
    - Add modules, exit the application, and restart to verify that the timetable persists.
    - Manually edit the `timetable.txt` file with valid and invalid data, then restart the application to confirm proper handling.

### **Performance Testing**
1. Populate the `timetable.txt` file with a large number of modules (e.g., 1000 entries) and verify that the application remains responsive.
2. Measure the response time for commands and ensure it does not exceed 1 second.

### **Logging**
1. Check the log file generated by the application:
    - Confirm that all user actions and errors are logged.
    - Verify that log entries are clear and provide sufficient detail for debugging.

