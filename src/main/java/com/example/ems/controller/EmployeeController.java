package com.example.ems.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
		
		Employee createdEmployee = employeeService.createEmployee(employeeDTO);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
	    try {
	        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
	        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	    }
	    catch(Exception e) {
	        System.out.println("Error updating employee: " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
	 try {
		 List<Employee> employees = employeeService.getAllEmployees();
		 return new ResponseEntity<>(employees,HttpStatus.OK);
	 }
	 catch(Exception e) {
		 System.out.println("Error retreiving employees" + e.getMessage());
		 throw e;
	 }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable Long id){
		try {
			employeeService.deleteEmployee(id);
			Map<String,String> response = new HashMap<>();
			response.put("message", "Employee record deleted successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(EntityNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			System.out.println("Error deleting employee"+ e.getMessage());
			throw e;
		}
	}
	

}

