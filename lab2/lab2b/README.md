# Lab 2b - Patient Appointment Management CLI (PAMS)

This module implements a Java CLI application that prepares patient data and exports it to JSON.

## Tech Stack

- Java 21
- Maven
- Jackson (JSON serialization)
- GitHub Actions CI (`.github/workflows/lab2b-build.yml`)

## Project Structure

- `src/main/java/edu/miu/cs489/lab2b/model/Patient.java` - patient entity with computed age
- `src/main/java/edu/miu/cs489/lab2b/PAMSApp.java` - app entry point and JSON export
- `output/patients.json` - generated runtime output

## Features

1. Loads the given patient dataset in memory
2. Computes each patient's current age from date of birth
3. Sorts by age in descending order (oldest first)
4. Exports sorted data to JSON file

## Build and Run

From this folder (`lab2/lab2b`):

```bash
mvn clean package
java -jar target/lab2b-1.0.0-jar-with-dependencies.jar
```

On successful run, JSON is generated at:

- `output/patients.json`

## CI/CD

GitHub Actions workflow:

- `lab2b-build.yml`
- Triggers on push and pull request events for `lab2/lab2b/**`
- Executes `mvn -B clean verify`

## Release Requirement (Manual)

To satisfy the assignment requirement:

1. Build executable JAR:
   - `mvn clean package`
2. Create GitHub release (manual)
3. Upload:
   - `target/lab2b-1.0.0-jar-with-dependencies.jar`

## Evidence

Screenshots for assignment evidence are stored in:

- `screenshots/`
