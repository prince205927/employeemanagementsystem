package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee (EmployeeDTO employeeDTO) {
		if(employeeRepository.existsByEmail(employeeDTO.getEmail())) {
			throw new RuntimeException("Error: Email is already in use ! ");
		}
		Employee employee = new Employee();
		mapDtoToEntity(employeeDTO, employee);
		
		return employeeRepository.save(employee);
	}
	
	private void mapDtoToEntity(EmployeeDTO dto, Employee entity) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setPosition(dto.getPosition());
		entity.setSalary(dto.getSalary());
		entity.setDepartment(dto.getDepartment());

	}
	
	public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Employee not found with id"+ id));
		if(!employee.getEmail().equals(employeeDTO.getEmail()) && employeeRepository.existsByEmail(employeeDTO.getEmail())) {
			throw new RuntimeException("Email is already in use by another employee");
		}
		updateEmployeeFields(employee,employeeDTO);
		return employeeRepository.save(employee);
	}
	
	private void updateEmployeeFields(Employee employee, EmployeeDTO dto) {
		if(dto.getFirstName()!=null) {
			employee.setFirstName(dto.getFirstName());
		}
		if(dto.getLastName()!=null) {
			employee.setLastName(dto.getLastName());
		}
		if(dto.getEmail()!=null) {
			employee.setEmail(dto.getEmail());
		}
		if(dto.getPhone()!=null) {
			employee.setPhone(dto.getPhone());
		}
		if(dto.getPosition()!=null) {
			employee.setPosition(dto.getPosition());
		}
		if(dto.getSalary()!=null) {
			employee.setSalary(dto.getSalary());
		}
		if(dto.getDepartment()!=null) {
			employee.setDepartment(dto.getDepartment());
		}
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Employee not found with id"+ id));
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public void deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Employee not found with id"+ id));
		employeeRepository.delete(employee);
	}
}
