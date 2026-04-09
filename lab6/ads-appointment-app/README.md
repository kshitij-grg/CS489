# ADS Dental Surgeries Appointment Management System

## Overview
This is a Spring Boot CLI application for managing dental surgery appointments using Spring Data JPA for database persistence. The system demonstrates complete CRUD operations, entity relationships, and Spring Boot integration with MySQL.

## Project Information
- **Course**: MIU APSD (Applied Software Development) - Lab 6
- **Framework**: Spring Boot 3.1.4
- **Java Version**: 21
- **Build Tool**: Maven 3.6+
- **Database**: MySQL 8.0+

## Project Structure
- **model/**: Entity classes (Address, Patient, Dentist, Surgery, Appointment, User, Role)
- **repository/**: Spring Data JPA repositories for database operations
- **service/**: Service interfaces and implementations for business logic
- **data/**: Data initialization component that populates sample data

## Features

### Entity Relationships
- **Address**: Used by Patients, Dentists, and Surgeries (One-to-One)
- **Patient**: Has multiple Appointments (One-to-Many)
- **Dentist**: Has multiple Appointments (One-to-Many)
- **Surgery**: Has multiple Appointments (One-to-Many)
- **Appointment**: Links Patient, Dentist, and Surgery (Many-to-One)
- **Role**: Office Manager role for Users (One-to-Many)
- **User**: Office Manager users with roles (Many-to-One)

### Database Tables
The application creates the following tables:
1. `addresses` - Address information
2. `patients` - Patient records (P100, P106, P108, P105, P110)
3. `dentists` - Dentist records (D001, D002, D003)
4. `surgeries` - Surgery/clinic records (S10, S15, S13)
5. `appointments` - Appointment records (6 sample appointments)
6. `roles` - User roles
7. `users` - User accounts (office managers)

### Sample Data
The application automatically populates the database with:
- 5 Patients
- 3 Dentists
- 3 Surgeries
- 6 Appointments
- 1 Role
- 2 Users

### CRUD Operations
The application demonstrates:
- **CREATE**: Data initialization with sample data via DataInitializer
- **READ**: List all patients, dentists, surgeries, appointments
- **READ**: Get by ID or specific criteria
- **READ**: Get appointments by patient or dentist number
- **UPDATE**: Service methods support updating entities
- **DELETE**: Service methods support deleting entities

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ads_dental_db
spring.datasource.username=ads_user
spring.datasource.password=test1234

# JPA/Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

## Project Structure Details

```
ads-appointment-app/
├── pom.xml                                      # Maven configuration
├── README.md                                    # Project documentation
├── .gitignore                                   # Git ignore rules
├── src/
│   ├── main/
│   │   ├── java/edu/miu/cs/cs489/lab6/adsappointment/
│   │   │   ├── ADSAppointmentCliApp.java        # Main application with CRUD demos
│   │   │   ├── model/                           # Entity classes
│   │   │   │   ├── Address.java
│   │   │   │   ├── Patient.java
│   │   │   │   ├── Dentist.java
│   │   │   │   ├── Surgery.java
│   │   │   │   ├── Appointment.java
│   │   │   │   ├── User.java
│   │   │   │   └── Role.java
│   │   │   ├── repository/                      # Spring Data JPA Repositories
│   │   │   │   ├── AddressRepository.java
│   │   │   │   ├── PatientRepository.java
│   │   │   │   ├── DentistRepository.java
│   │   │   │   ├── SurgeryRepository.java
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── RoleRepository.java
│   │   │   ├── service/                         # Service interfaces
│   │   │   │   ├── PatientService.java
│   │   │   │   ├── DentistService.java
│   │   │   │   ├── SurgeryService.java
│   │   │   │   └── AppointmentService.java
│   │   │   ├── service/impl/                    # Service implementations
│   │   │   │   ├── PatientServiceImpl.java
│   │   │   │   ├── DentistServiceImpl.java
│   │   │   │   ├── SurgeryServiceImpl.java
│   │   │   │   └── AppointmentServiceImpl.java
│   │   │   └── data/
│   │   │       └── DataInitializer.java         # Sample data initialization
│   │   └── resources/
│   │       └── application.properties           # Spring Boot configuration
│   └── test/java/                               # Test classes
└── target/                                      # Build output
```

## Running the Application

### Prerequisites
1. Install Java 21 or later
2. Install MySQL Server
3. Install Maven 3.6+

### Setup Steps

1. **Start MySQL Server** (if not running)
   ```bash
   # macOS with Homebrew
   brew services start mysql

   # Or connect to your MySQL instance
   ```

2. **Create Database and User**
   ```sql
   CREATE DATABASE ads_dental_db;
   CREATE USER 'ads_user'@'localhost' IDENTIFIED BY 'test1234';
   GRANT ALL PRIVILEGES ON ads_dental_db.* TO 'ads_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Build the Project**
   ```bash
   cd ads-appointment-app
   mvn clean compile
   ```

4. **Run the Application**
   - **Using Maven:**
     ```bash
     mvn spring-boot:run
     ```

   - **Using JAR:**
     ```bash
     mvn clean package
     java -jar target/adsappointmentapp-0.0.1-SNAPSHOT.jar
     ```