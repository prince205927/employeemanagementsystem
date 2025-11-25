package com.example.ems.service;

import com.example.ems.dto.EmployeeCreationDTO;
import com.example.ems.entity.BaseEmployee;
import com.example.ems.entity.FullTimeEmployee;
import com.example.ems.entity.PartTimeEmployee;
import com.example.ems.repository.FullTimeEmployeeRepository;
import com.example.ems.repository.PartTimeEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeManagementService {
    
    private final FullTimeEmployeeRepository fullTimeRepository;
    private final PartTimeEmployeeRepository partTimeRepository;
    
    public BaseEmployee createEmployee(EmployeeCreationDTO dto) {
        if ("FULL_TIME".equalsIgnoreCase(dto.getType())) {
            return createFullTimeEmployee(dto);
        } else if ("PART_TIME".equalsIgnoreCase(dto.getType())) {
            return createPartTimeEmployee(dto);
        } else {
            throw new IllegalArgumentException("Invalid employee type. Use FULL_TIME or PART_TIME");
        }
    }
    
    private FullTimeEmployee createFullTimeEmployee(EmployeeCreationDTO dto) {
        validateFullTimeFields(dto);
        
        FullTimeEmployee employee = new FullTimeEmployee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setAnnualSalary(dto.getAnnualSalary());
        employee.setBonus(dto.getBonus());
        employee.setPaidLeaveDays(dto.getPaidLeaveDays());
        
        return fullTimeRepository.save(employee);
    }
    
    
	public FullTimeEmployee createFullTimeEmployee(String name, String email, String department, String position,Double annualSalary, Double bonus, Integer paidLeaveDays) {
	    EmployeeCreationDTO dto = new EmployeeCreationDTO();
	    dto.setName(name);
	    dto.setEmail(email);
	    dto.setDepartment(department);
	    dto.setPosition(position);
	    dto.setAnnualSalary(annualSalary);
	    dto.setBonus(bonus);
	    dto.setPaidLeaveDays(paidLeaveDays);
	
	    return createFullTimeEmployee(dto);
	
	}
    
    private PartTimeEmployee createPartTimeEmployee(EmployeeCreationDTO dto) {
        validatePartTimeFields(dto);
        
        PartTimeEmployee employee = new PartTimeEmployee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setHourlyRate(dto.getHourlyRate());
        employee.setHoursPerWeek(dto.getHoursPerWeek());
        employee.setWeeksPerYear(dto.getWeeksPerYear());
        
        return partTimeRepository.save(employee);
    }
    
    public BaseEmployee getEmployeeById(Long id, String type) {
        if ("FULL_TIME".equalsIgnoreCase(type)) {
            return fullTimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Full-time employee not found with id: " + id));
        } else if ("PART_TIME".equalsIgnoreCase(type)) {
            return partTimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part-time employee not found with id: " + id));
        } else {
            throw new IllegalArgumentException("Invalid type. Use FULL_TIME or PART_TIME");
        }
    }
    
    public List<BaseEmployee> getAllEmployees(String type) {
        List<BaseEmployee> employees = new ArrayList<>();
        
        if (type == null || "ALL".equalsIgnoreCase(type)) {
            employees.addAll(fullTimeRepository.findAll());
            employees.addAll(partTimeRepository.findAll());
        } else if ("FULL_TIME".equalsIgnoreCase(type)) {
            employees.addAll(fullTimeRepository.findAll());
        } else if ("PART_TIME".equalsIgnoreCase(type)) {
            employees.addAll(partTimeRepository.findAll());
        }
        
        return employees;
    }
    
    public Map<String, Object> calculateSalary(Long id, String type) {
        BaseEmployee employee = getEmployeeById(id, type);
        
        Map<String, Object> salaryDetails = new HashMap<>();
        salaryDetails.put("employeeId", employee.getId());
        salaryDetails.put("employeeName", employee.getName());
        salaryDetails.put("employeeType", employee.getEmployeeType());
        salaryDetails.put("monthlySalary", employee.calculateSalary()); 
        
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee fte = (FullTimeEmployee) employee;
            salaryDetails.put("annualBaseSalary", fte.getAnnualSalary());
            salaryDetails.put("bonus", fte.getBonus());
            salaryDetails.put("totalAnnualSalary", fte.calculateAnnualSalary());
            salaryDetails.put("paidLeaveDays", fte.getPaidLeaveDays());
        } else if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee pte = (PartTimeEmployee) employee;
            salaryDetails.put("hourlyRate", pte.getHourlyRate());
            salaryDetails.put("hoursPerWeek", pte.getHoursPerWeek());
            salaryDetails.put("weeksPerYear", pte.getWeeksPerYear());
            salaryDetails.put("estimatedAnnualSalary", pte.calculateAnnualSalary());
        }
        
        return salaryDetails;
    }
    
    public void deleteEmployee(Long id, String type) {
        if ("FULL_TIME".equalsIgnoreCase(type)) {
            if (!fullTimeRepository.existsById(id)) {
                throw new RuntimeException("Full-time employee not found with id: " + id);
            }
            fullTimeRepository.deleteById(id);
        } else if ("PART_TIME".equalsIgnoreCase(type)) {
            if (!partTimeRepository.existsById(id)) {
                throw new RuntimeException("Part-time employee not found with id: " + id);
            }
            partTimeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid type. Use FULL_TIME or PART_TIME");
        }
    }
    
    private void validateFullTimeFields(EmployeeCreationDTO dto) {
        if (dto.getAnnualSalary() == null || dto.getAnnualSalary() <= 0) {
            throw new IllegalArgumentException("Annual salary is required for full-time employees");
        }
        if (dto.getBonus() == null || dto.getBonus() < 0) {
            throw new IllegalArgumentException("Bonus is required for full-time employees");
        }
        if (dto.getPaidLeaveDays() == null || dto.getPaidLeaveDays() < 0) {
            throw new IllegalArgumentException("Paid leave days is required for full-time employees");
        }
    }
    
    public BaseEmployee updateEmployee(Long id, String type, EmployeeCreationDTO dto) {
        if ("FULL_TIME".equalsIgnoreCase(type)) {
            return updateFullTimeEmployee(id, dto);
        } else if ("PART_TIME".equalsIgnoreCase(type)) {
            return updatePartTimeEmployee(id, dto);
        } else {
            throw new IllegalArgumentException("Invalid employee type. Use FULL_TIME or PART_TIME");
        }
    }

    private FullTimeEmployee updateFullTimeEmployee(Long id, EmployeeCreationDTO dto) {
        FullTimeEmployee employee = fullTimeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Full-time employee not found with id: " + id));
        
        validateFullTimeFields(dto);
        
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setAnnualSalary(dto.getAnnualSalary());
        employee.setBonus(dto.getBonus());
        employee.setPaidLeaveDays(dto.getPaidLeaveDays());
        
        return fullTimeRepository.save(employee);
    }

    private PartTimeEmployee updatePartTimeEmployee(Long id, EmployeeCreationDTO dto) {
        PartTimeEmployee employee = partTimeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Part-time employee not found with id: " + id));
        
        validatePartTimeFields(dto);
        
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setHourlyRate(dto.getHourlyRate());
        employee.setHoursPerWeek(dto.getHoursPerWeek());
        employee.setWeeksPerYear(dto.getWeeksPerYear());
        
        return partTimeRepository.save(employee);
    }
    
    private void validatePartTimeFields(EmployeeCreationDTO dto) {
        if (dto.getHourlyRate() == null || dto.getHourlyRate() <= 0) {
            throw new IllegalArgumentException("Hourly rate is required for part-time employees");
        }
        if (dto.getHoursPerWeek() == null || dto.getHoursPerWeek() <= 0) {
            throw new IllegalArgumentException("Hours per week is required for part-time employees");
        }
        if (dto.getWeeksPerYear() == null || dto.getWeeksPerYear() <= 0) {
            throw new IllegalArgumentException("Weeks per year is required for part-time employees");
        }
    }
}