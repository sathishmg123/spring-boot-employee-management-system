package com.keima.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDetails {

    private String email;
    private String password;
}
