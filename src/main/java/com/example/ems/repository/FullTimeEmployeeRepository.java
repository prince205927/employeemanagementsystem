package com.example.ems.repository;

import com.example.ems.entity.FullTimeEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FullTimeEmployeeRepository extends JpaRepository<FullTimeEmployee, Long> {
}