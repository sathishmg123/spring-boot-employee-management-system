package com.keima.employeemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keima.employeemanagementsystem.model.CompanyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ipAddress;
    private String email;

    public Company(String name, String ipAddress, String email, String password) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.email = email;
        this.password = password;
    }

    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        if(employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
    }

    public void removeEmployee(Long id) {
        employees.removeIf(employee -> employee.getId().equals(id));
    }

    public static Company fromModel(CompanyModel model) {
        return new Company(
                model.getName(),
                model.getIpAddress(),
                model.getEmail(),
                model.getPassword()
        );
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", employees=" + employees +
                '}';
    }
}
