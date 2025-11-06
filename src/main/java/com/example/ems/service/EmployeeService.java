package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
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
}
