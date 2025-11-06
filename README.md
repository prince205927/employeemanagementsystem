
# Employee Management System

## Overview
The **Employee Management System** is a Spring Boot application designed to manage employee records efficiently. It provides RESTful APIs for user registration, login, and CRUD operations on employee data.


## Features
- **User Registration**
- **User Login**
- **Add Employee** – Create new employee records
- **View Employees** – Retrieve all or specific employee details
- **Update Employee** – Modify existing employee information
- **Delete Employee** – Remove employee records


## Tech Stack
- **Java** – Core programming language
- **Spring Boot** – Backend framework
- **Maven** – Build and dependency management
- **SQLite** – Data storage
- **REST API** – Communication layer

### Prerequisites
- **Java 17+**
- **Maven 3.8+**
- **Spring Boot**

### Installation
1. Clone the repository:
   
   git clone https://github.com/prince205927/employeemanagementsystem.git

2. Navigate to the repository: 

    cd employeemanagementsystem

3. Build the project : 

    mvn clean install

4. Run the application :

    mvn spring-boot:run

##  API Endpoints

| Method  | Endpoint                  | Description                |
|---------|---------------------------|---------------------------|
| POST    | `/api/auth/register`          | Register a new user      |
| POST    | `/api/auth/login`             | User login               |
| POST    | `/api/employees`         | Add new employee         |
| GET     | `/api/employees`         | Get all employees        |
| GET     | `/api/employees/{id}`    | Get employee by ID       |
| PUT     | `/api/employees/{id}`    | Update employee details  |
| DELETE  | `/api/employees/{id}`    | Delete employee          |


## Object-Oriented Programming (OOP) Concepts Used

This project leverages core OOP principles to ensure modularity, maintainability, and scalability:

### 1. **Encapsulation**
- All employee-related data (e.g., `name`, `email`, `department`, `salary`) is encapsulated within the `Employee` class.
- Access to fields is controlled using **getters and setters**, ensuring data integrity.

### 2. **Inheritance**
- Common functionality is abstracted into **base classes** or **interfaces**.
- For example, service classes may implement interfaces like `EmployeeService` to enforce a contract.

### 3. **Polymorphism**
- Achieved through **method overriding** in service implementations.
- Example: Different implementations of `saveEmployee()` or `findEmployee()` can exist for different data sources.

### 4. **Abstraction**
- Business logic is separated from implementation details using **interfaces** and **abstract classes**.
- Controllers interact with services without knowing the underlying persistence logic.

### 5. **Composition**
- Classes like `EmployeeController` use service classes via **dependency injection** rather than creating objects directly.
- Promotes loose coupling and better testability.



