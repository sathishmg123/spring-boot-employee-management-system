package com.keima.employeemanagementsystem.repository;

import com.keima.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
