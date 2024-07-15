# Transaction Service

The **Transaction Service** is a **Spring Boot application** designed to handle transaction authorization and management
using an **in-memory H2 database**. It provides endpoints to authorize transactions, retrieve transactions by account,
and integrates with merchant information for transaction processing.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Setup](#setup)
    - [Prerequisites](#prerequisites)
    - [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Database Configuration](#database-configuration)
- [Project Structure](#project-structure)
- [Testing](#testing)

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.8-SNAPSHOT**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Lombok**
- **MapStruct**
- **Maven**

## Setup

### Prerequisites

- **Java 17** installed
- **Maven** installed

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone <repository_url>

2. **Navigate to the project directory:**
    ```bash
   cd transaction-service

3. **Build the project:**
    ```bash
    mvn clean install

4. **Run the application:**
     ```bash
   mvn spring-boot:run

5. **The application will start at http://localhost:8080.**

### Endpoints

- **POST /transactions/authorize**
- **GET /transactions/account/{account_id}**
    - Retrieves transactions associated with a specific account.

### Database Configuration

The application uses an **H2 in-memory database** for development. The database schema and initial data are configured
using schema.sql and data.sql files located in the src/main/resources directory. These scripts are executed
automatically on application startup (spring.datasource.initialization-mode=always).

### Project Structure

The project follows a standard Maven directory structure and is organized as follows:

- **controllers:** Contains REST controllers handling incoming HTTP requests.
- **domains:** Includes request and response DTOs for transaction handling.
- **enums:** Enumerations for MCC (Merchant Category Code), transaction codes, and balance types.
- **exceptions:** Custom exception handling and error messages.
- **gateways:** Interfaces and implementations for interacting with data repositories (JPA repositories).
- **mappers:** MapStruct mappers for converting between DTOs and entity objects.
- **repositories:** Spring Data JPA repositories for database operations.
- **services:** Business logic services for transaction authorization and account management.
- **utils:** Constants and utility classes.

### Testing

Unit tests are already implemented within the project to verify the functionality of services and controllers. These
tests cover various scenarios, including authorization logic, exception handling, and service methods.




