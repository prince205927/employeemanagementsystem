package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "part_time_employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class PartTimeEmployee extends BaseEmployee {
    
    @Column(nullable = false)
    private Double hourlyRate;
    
    @Column(nullable = false)
    private Integer hoursPerWeek;
    
    @Column(nullable = false)
    private Integer weeksPerYear;
    
    @Override
    public Double calculateSalary() {
        return (hourlyRate * hoursPerWeek * weeksPerYear) / 12;
    }
    
    @Override
    public String getEmployeeType() {
        return "PART_TIME";
    }
    
    public Double calculateAnnualSalary() {
        return hourlyRate * hoursPerWeek * weeksPerYear;
    }
}