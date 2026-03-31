# Lab 2a - Employee Pension CLI

This module implements a Java CLI application for managing Employee and Pension Plan data.

## Tech Stack

- Java 21
- Maven
- Jackson (JSON serialization)
- GitHub Actions CI (`.github/workflows/lab2a-build.yml`)

## Project Structure

- `src/main/java/edu/miu/cs489/lab2a/model` - `Employee`, `PensionPlan`
- `src/main/java/edu/miu/cs489/lab2a/data` - in-memory dataset (`DataStore`)
- `src/main/java/edu/miu/cs489/lab2a/service` - sorting and enrollment report logic
- `src/main/java/edu/miu/cs489/lab2a/App.java` - CLI entry point

## Features

1. Print all employees as JSON  
   - Sorted by yearly salary (descending), then last name (ascending)
2. Print quarterly upcoming enrollees report as JSON  
   - Employees without pension plan
   - Minimum salary: 100,000.00
   - Eligibility based on 1-year employment rule
   - Sorted by employment date (descending)

## Run Locally

From this folder (`lab2/lab2a`):

```bash
mvn clean verify
mvn exec:java -Dexec.mainClass=edu.miu.cs489.lab2a.App
```

## CI/CD

GitHub Actions workflow:

- `lab2a-build.yml`
- Triggers on push and pull request events for `lab2/lab2a/**`
- Executes `mvn -B clean verify`

## Evidence

Screenshots for assignment evidence are stored in:

- `screenshots/`
