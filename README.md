# ModMate

🎉 Welcome to **ModMate**, your ultimate companion for managing your modules and timetables at the National University of Singapore (NUS)!

📚 ModMate is designed to help students efficiently organize their academic schedules, bookmark favorite modules, and explore detailed module information.

✨ Whether you're a student looking to streamline your academic life, an instructor reviewing this project, or a curious reader, ModMate offers a simple and intuitive command-line interface to create personalized timetables, manage modules, and much more.

💡 This project is part of the CS2113 course at NUS and was taken in AY2024-2025 Semester 2.

## Setting up in IntelliJ

Prerequisites: JDK 17 (use the exact version), update IntelliJ to the most recent version.

1. **Ensure IntelliJ JDK 17 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 17 in a previous IntelliJ project.
2. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. **Verify the setup**: After the importing is complete, locate the `src/main/java/modmate/Main.java` file, right-click it, and choose `Run Main.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes

   > Task :Main.main()
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Build Automation Using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e., the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O Redirection Tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit Tests

* This project includes comprehensive JUnit tests located in the `src/test/java/modmate` directory. Key test classes include:
   - `modmate.user`: Includes tests for user-related operations, such as managing timetables and bookmarks (`UserTest`) and timetable-related operations (`ScheduleTest`).
   - `modmate.mod`: Includes tests for module-related functionality, such as `ModTest` and `CondensedModTest` for streamlined module operations.

* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI Using GitHub Actions

The project uses [GitHub Actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub Actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

📖 Useful links:

* [📘 User Guide](docs/UserGuide.md) - Learn how to get started and explore ModMate's features.
* [💻 Developer Guide](docs/DeveloperGuide.md) - Dive into the technical details, including design decisions and coding standards.
* [👥 About Us](docs/AboutUs.md) - Meet the team behind ModMate.
