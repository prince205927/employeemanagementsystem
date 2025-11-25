
# Employee Management System

## Overview
The **Employee Management System** is a Spring Boot application designed to manage employee records efficiently. It provides RESTful APIs for user registration, login, and CRUD operations on employee data.


## Features
- **User Registration**
- **Create Full-Time and Part-Time Employees**
- **Retrieve Employees with Filtering**
- **Automatic Salary Calculation (different for each type)** 
- **Delete Employee Records**


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

| Method | Endpoint                        | Description                  | Query Params                              |
|--------|---------------------------------|------------------------------|-------------------------------------------|
| POST   | `/api/auth/register`            | Register a new user          | None                                      |
| POST   | `/api/auth/login`               | User login                   | None                                      |
| POST   | `/api/employees`                | Create employee (any type)   | None                                      |
| GET    | `/api/employees`                | Get all employees            | `type=ALL/FULL_TIME/PART_TIME`           |
| GET    | `/api/employees/{id}`           | Get employee by ID           | `type=FULL_TIME/PART_TIME`               |
| GET    | `/api/employees/{id}/salary`    | Calculate salary             | `type=FULL_TIME/PART_TIME`               |
| PUT    | `/api/employees/{id}`           | Update employee details      | `type=FULL_TIME/PART_TIME`               |
| DELETE | `/api/employees/{id}`           | Delete employee              | `type=FULL_TIME/PART_TIME`               |



## Object-Oriented Programming (OOP) Concepts Used

This project leverages core OOP principles to ensure modularity, maintainability, and scalability:

### 1. **Inheritance**
- Child classes inherit properties and methods from parent classes.

#### Example 1: BaseEntity (Abstract Parent Class)
```java
public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Common fields for all entities
}
```

**Child Classes:**
- `User.java` extends `BaseEntity`
- `BaseEmployee.java` extends `BaseEntity`

#### Example 2: BaseEmployee (Abstract Parent Class)
```java
public abstract class BaseEmployee extends BaseEntity {
    private String name;
    private String email;
    private String department;
    private String position;
    
    // Abstract methods that children must implement
    public abstract Double calculateSalary();
    public abstract String getEmployeeType();
}
```

**Child Classes:**
- `FullTimeEmployee.java` extends `BaseEmployee`
- `PartTimeEmployee.java` extends `BaseEmployee`

### 2. **Dynamic Polymorphism**
- Method behavior is determined at runtime based on the actual object type.
- The method that implements polymorphism here is **calculateSalary()**

**In FullTimeEmployee.java:**
```java
@Override
public Double calculateSalary() {
    return (annualSalary + bonus) / 12;  
}
```

**In PartTimeEmployee.java:**
```java
@Override
public Double calculateSalary() {
    return (hourlyRate * hoursPerWeek * weeksPerYear) / 12;  
}
```

```java
public Map<String, Object> calculateSalary(Long id, String type) {
    BaseEmployee employee = getEmployeeById(id, type);
    // It calls different implementations based on actual object type
    salaryDetails.put("monthlySalary", employee.calculateSalary()); 
}
```

### 3. **Static Polymorphism**
- Method behavior is determined at compile time based on method signature. (Method overloading)
- Overloaded createEmployee() Methods
```java
public BaseEmployee createEmployee(String name, String email, String type) {
    EmployeeCreationDTO dto = new EmployeeCreationDTO();
    dto.setName(name);
    dto.setEmail(email);
    dto.setType(type);
    return createEmployee(dto);
}
public BaseEmployee createEmployee(String name, String email, String department, String type) {
    EmployeeCreationDTO dto = new EmployeeCreationDTO();
    dto.setName(name);
    dto.setEmail(email);
    dto.setDepartment(department);
    dto.setType(type);
    return createEmployee(dto);
}
```

### 4. **Abstraction**
- Hiding implementation details and showing only essential features.

**BaseEmployee.java:**
```java
public abstract class BaseEmployee extends BaseEntity {
    // Abstract methods
    public abstract Double calculateSalary();
    public abstract String getEmployeeType();
}
```

### 5. **Encapsulation**
- Bundling data and methods together, hiding internal state.
**In FullTimeEmployee.java:**
```java
@Entity
public class FullTimeEmployee extends BaseEmployee {
    
    // Private fields 
    private Double annualSalary;
    private Double bonus;
    private Integer paidLeaveDays;
    
    // Public getters and setters
    public Double getAnnualSalary() { return annualSalary; }
    public void setAnnualSalary(Double annualSalary) { this.annualSalary = annualSalary; }
}
```



