# Lab 11 - Applied Software Development

This Lab 11 folder contains two separate deliverables:

1. Part 1: PDF-based Java/JUnit tasks (ArrayFlattener and ArrayReversor)
2. Part 2: New version of Lab7 Web API with required testing work

## Folder Structure

- `src/` - Part 1 source and tests
- `adswebapi-part2/` - Part 2 Spring Boot Web API project and tests

## Part 1 Deliverables

Location: `lab11/src/edu/miu/cs/cs489appsd/lab11/`

### Array Flattener

- `arrayflattener/ArrayFlattener.java`
- `arrayflattener/ArrayFlattenerTest.java`
- `arrayflattener/ArrayFlattenerTestSuite.java`

Implemented:
- `flattenArray(int[][])`
- Test for valid nested input
- Test for null input
- JUnit suite for the two tests

### Array Reversor

- `arrayreversor/ArrayFlattenerService.java`
- `arrayreversor/ArrayReversor.java`
- `arrayreversor/ArrayReversorTest.java`
- `arrayreversor/ArrayReversorTestCases.java`

Implemented:
- `reverseArray(int[][])` using `ArrayFlattenerService`
- Mockito-based unit tests (valid input and null input)
- Verification that `flattenArray(...)` is invoked
- JUnit suite named `ArrayReversorTestCases`

## Part 2 Deliverables

Location: `lab11/adswebapi-part2/`

This is the new version of the Lab7 Web API project with testing requirements implemented.

### Required Tests Implemented

1. Integration tests for `PatientService.findPatientById(...)`
- Existing patient ID case
- Invalid patient ID case
- File: `src/test/java/edu/miu/cs/cs489/lab6/adsappointment/service/PatientServiceIntegrationTest.java`

2. Unit test (mocking) for PatientController list endpoint
- Endpoint: `GET /adsweb/api/v1/patients`
- File: `src/test/java/edu/miu/cs/cs489/lab6/adsappointment/controller/PatientControllerUnitTest.java`

### Notes for Part 2 Runtime

- Tests are configured to use the same MySQL datasource settings as Lab7 (from `src/main/resources/application.properties`).
- Ensure MySQL is running and database credentials are valid before running tests.

## How to Run Part 2 Tests

From `lab11/adswebapi-part2`:

```bash
mvn -Dtest=PatientServiceIntegrationTest,PatientControllerUnitTest test
```

Or run all tests:

```bash
mvn test
```
