package com.keima.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmployeeModel {

    private String firstName;
    private String lastName;
    private String email;
    private double baseSalary;
    private String password;
}
