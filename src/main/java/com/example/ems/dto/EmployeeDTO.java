package com.example.ems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("firstName")
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @JsonProperty("phone")
    private String phone;
    
    @JsonProperty("position")
    private String position;
    
    @JsonProperty("salary")
    @Positive(message = "Salary must be positive")
    private Double salary;
    
    @JsonProperty("department")
    private String department;
}