package com.example.ems.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationDTO {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @NotBlank(message = "Position is required")
    private String position;
    
    @NotBlank(message = "Type is required: FULL_TIME or PART_TIME")
    private String type; 
    
    // Full-time fields
    private Double annualSalary;
    private Double bonus;
    private Integer paidLeaveDays;
    
    // Part-time fields
    private Double hourlyRate;
    private Integer hoursPerWeek;
    private Integer weeksPerYear;
}