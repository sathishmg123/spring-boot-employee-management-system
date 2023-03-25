package com.keima.employeemanagementsystem.repository;

import com.keima.employeemanagementsystem.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusRepository extends JpaRepository<Bonus, Long> {
}
