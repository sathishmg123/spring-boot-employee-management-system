package com.keima.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyModel {
    private String name;
    private String ipAddress;
    private String email;
    private String password;
}
