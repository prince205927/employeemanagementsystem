package com.example.ems.repository;

import com.example.ems.entity.PartTimeEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTimeEmployeeRepository extends JpaRepository<PartTimeEmployee, Long> {
}