# Split bill platform automation tests

## Table of Contents

1. Environment requirements
2. Tests running

### 1. Environment requirements and plugins set up

**Tools to be installed:**

- Java Development Kit 11 (JDK11)
- Gradle 6.x should be present on environment
- Google Chrome and Firefox browsers installed

**IntelliJ Idea Plugins to be installed and configured:**

- **Gradle** plugin should be installed.
- **Lombok** plugin should be installed.
- **SonarLint** plugin should be installed

### 2. Tests running and reporting

In order to run test perform the following actions:

1. Open terminal on project **root** folder.
2. For running test use command the next *gradle* command:

```
./gradlew clean test -DbrowserName=chrome
```  

as *-DbrowserName* property value you can use values: **chrome**, **firefox**

3. For generating test report use command the next *gradle* command:

```
./gradlew allureReport
``` 

Regardless exception the report is generated.

4. For opening test report use command the next *gradle* command:

```
./gradlew allureServe
``` 