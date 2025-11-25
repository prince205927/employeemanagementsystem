package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "full_time_employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class FullTimeEmployee extends BaseEmployee {
    
    @Column(nullable = false)
    private Double annualSalary;
    
    @Column(nullable = false)
    private Double bonus;
    
    @Column(nullable = false)
    private Integer paidLeaveDays;
    
    @Override
    public Double calculateSalary() {
        return (annualSalary + bonus) / 12;
    }
    
    @Override
    public String getEmployeeType() {
        return "FULL_TIME";
    }
    
    public Double calculateAnnualSalary() {
        return annualSalary + bonus;
    }
}