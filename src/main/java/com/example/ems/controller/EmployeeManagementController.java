package com.example.ems.controller;

import com.example.ems.dto.EmployeeCreationDTO;
import com.example.ems.entity.BaseEmployee;
import com.example.ems.service.EmployeeManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeManagementController {
    
    private final EmployeeManagementService employeeService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEmployee(@Valid @RequestBody EmployeeCreationDTO dto) {
        BaseEmployee employee = employeeService.createEmployee(dto);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee created successfully");
        response.put("employee", employee);
        response.put("monthlySalary", employee.calculateSalary());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(
            @PathVariable Long id,
            @RequestParam String type) {
        BaseEmployee employee = employeeService.getEmployeeById(id, type);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee retrieved successfully");
        response.put("employee", employee);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(required = false, defaultValue = "ALL") String type) {
        List<BaseEmployee> employees = employeeService.getAllEmployees(type);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employees retrieved successfully");
        response.put("type", type);
        response.put("count", employees.size());
        response.put("employees", employees);
        return ResponseEntity.ok(response);
    }
    
   @GetMapping("/{id}/salary")
    public ResponseEntity<Map<String, Object>> calculateSalary(
            @PathVariable Long id,
            @RequestParam String type) {
        Map<String, Object> salaryDetails = employeeService.calculateSalary(id, type);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Salary calculated successfully");
        response.put("salaryDetails", salaryDetails);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(
            @PathVariable Long id,
            @RequestParam String type) {
        employeeService.deleteEmployee(id, type);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        response.put("deletedId", id.toString());
        return ResponseEntity.ok(response);
    }
}