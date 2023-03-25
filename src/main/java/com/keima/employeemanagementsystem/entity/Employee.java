package com.keima.employeemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keima.employeemanagementsystem.model.EmployeeModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysPresent;
    private double baseSalary;
    private LocalDate lastAttendance;

    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bonus> bonuses;

    public Employee(String firstName, String lastName, String email, double baseSalary, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.baseSalary = baseSalary;
        this.password = password;
    }

    public void addBonus(Bonus bonus) {
        if(bonuses == null) {
            bonuses = new ArrayList<>();
        }
        bonuses.add(bonus);
    }

    public void removeBonus(Long id) {
        bonuses.removeIf(bonus -> bonus.getId().equals(id));
    }

    public static Employee fromModel(EmployeeModel employeeModel) {
        return new Employee(
                employeeModel.getFirstName(),
                employeeModel.getLastName(),
                employeeModel.getEmail(),
                employeeModel.getBaseSalary(),
                employeeModel.getPassword()
        );
    };
}
