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
- **Purpose**: Allows users to create, update, and view multiple academic schedules.
- **Implementation**:
      Two core methods in the `User` class:
      1. `addTimetable(String timetableName)` - Method to add a new timetable for the user
      2. `getTimetable(String timetableName)` - Method to display the user's timetables (mods included in the timetable)
     These methods are supported by `Schedule` class for storage structure
      
      `addTimetable(String timetableName)` first checks whether there are any duplicate timetables with the same name. It checks through a list called `List<Schedule> timetables`
      and if duplicate timetable found, an error message, "Timetable 'X' already exists", will be displayed. If no duplicate timetable found, a new Schedule object is instantiated  
      and a new timetable of `timetableName` will be created. It is added to `timetables` list, afterwhich a success message, "Timetable 'X' created successfully", is displayed.

      `getTimetable(String timetableName)` searches through a list called `timetables`, as mentioned earlier. If timetable with corresponding `timetableName` is found,  
      a formatted String is returned which shows, the timetable name, all added modules and module details. If timetable is not found, 
      an error message, "Timetable 'X' not found", will be displayed.

- **Design Rationale**: Usage of List<Schedule> allowed for maintainable insertion order of timetables and straightforward iteration. 
Moreover, with the timetable duplication checking it allows for unnecessary object creation and potential naming conflicts.
- **Possible Extensions**: Merging of multiple timetables

The following UML Sequence diagram effectively shows how these two methods are implemented with the inclusion of user inputs of `createtimetable` and `timetable` 
for `addTimetable(String timetableName)` and `getTimetable(String timetableName)` respectively.
![image.png](image/image.png)

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

### **Testing Commands**
1. **Launching the Application**:
    - Run the `Main` class located in `src/main/java/modmate/Main.java`.
    - Verify that the application starts without errors and displays the welcome message.

2. **Adding Modules**:
    - Enter a valid `add` command (e.g., `add CS2113`) and verify that the module is added to the in-memory timetable.
    - Test edge cases such as adding duplicate modules or invalid module codes (e.g., `add XYZ123`) and confirm appropriate error messages are displayed.

3. **Viewing Timetable**:
    - Use the `view` command to display all added modules.
    - Verify that the output matches the expected timetable, including module codes, names, and semesters.

4. **Removing Modules**:
    - Enter a valid `remove` command (e.g., `remove CS2113`) and ensure the module is removed from the in-memory timetable.
    - Test edge cases such as removing non-existent modules and verify the application handles them gracefully.

5. **Searching Modules**:
    - Test the `search` command with valid module codes or names (e.g., `search CS2113` or `search Software`).
    - Verify that the search results are accurate and displayed correctly.
    - Test invalid or partial inputs and confirm that the application provides meaningful feedback.

6. **Bookmarking Modules**:
    - Use the `bookmark` command to mark a module as a favorite (e.g., `bookmark CS2113`).
    - Verify that the bookmarked module is displayed when using the `getbookmarks` command.
    - Test edge cases such as bookmarking non-existent modules.

7. **Timetable Creation**:
    - Test the `createtimetable` command to generate a new timetable.
    - Verify that the timetable is initialized correctly and does not overwrite existing data unless explicitly confirmed.

### **Error Handling**
1. Enter invalid commands (e.g., `add123`) and confirm that the application displays appropriate error messages.
2. Test edge cases:
    - Empty input: Press Enter without typing any command.
    - Invalid module codes: Use non-existent or malformed module codes (e.g., `add XYZ123`).
    - Excessively long inputs: Enter commands with overly long descriptions and verify the application handles them gracefully.

### **API Integration**
1. Ensure the application can fetch module data from the NUSMods API:
    - Disconnect from the internet and attempt to fetch data to verify error handling.
    - Reconnect and confirm that valid data is retrieved and displayed correctly.
    - Test commands that rely on API data (e.g., `search` or `add`) to ensure they function as expected.

### **Performance Testing**
1. Populate the in-memory timetable with a large number of modules (e.g., 1000 entries) and verify that the application remains responsive.
2. Measure the response time for commands such as `add`, `view`, and `search` to ensure they do not exceed 1 second.

### **Logging**
1. Check the log file generated by the application:
    - Confirm that all user actions and errors are logged.
    - Verify that log entries are clear and provide sufficient detail for debugging.
    - Test scenarios that generate warnings or errors (e.g., invalid commands, API failures) and ensure they are logged appropriately.

### **Advanced Scenarios**
1. **Concurrent Modifications**:
    - Simulate concurrent user actions (e.g., adding and removing modules in quick succession) and verify that the application handles them correctly.
2. **Stress Testing**:
    - Continuously add and remove modules in rapid succession to test the application's stability.
3. **Cross-Component Interactions**:
    - Test commands that involve multiple components (e.g., adding a module and immediately bookmarking it) to ensure seamless integration.

These instructions are tailored to the application's structure and functionality, ensuring comprehensive manual testing.
