# Task WebDriver

This project is a Java-based automated testing framework using Maven, TestNG, and Selenium WebDriver (selenide).

## Features

- Automated UI tests with Selenium WebDriver (Selenide)
- Test management with TestNG
- Maven for build and dependency management

## Prerequisites

- Java 8 or higher
- Maven 3.x
- ChromeDriver or another WebDriver executable in your system PATH

## Project Structure

- `src/main/java` — Application source code (if any)
- `src/test/java` — Test source code
- `pom.xml` — Maven configuration file

## How to Run Tests with Maven

1. Open a terminal in the project root directory.
2. Run the following command to execute all TestNG tests:

    ```bash
    mvn clean test
    ```

This will:
- Clean previous build artifacts
- Compile the code
- Run all tests annotated with `@Test`

## Customizing Test Execution

- To run a specific test class:

    ```bash
    clean test -Dxml= (smoke/regression/all) -Denv= (dev/prod) -Dbrowser= (chrome, edge, firefox)
    ```

- To generate a test report, check the `target/surefire-reports` directory after the run.

## Additional Notes

- Make sure your WebDriver executable (e.g., `chromedriver.exe`) is available in your system PATH or specify its location in your test setup.
- You can configure TestNG options in the `pom.xml` or via a `testng.xml` suite file.

---