package com.keima.employeemanagementsystem.repository;

import com.keima.employeemanagementsystem.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Optional<Company> findFirstByEmail(String email);
}
