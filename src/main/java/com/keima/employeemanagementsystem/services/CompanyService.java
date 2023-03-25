package com.keima.employeemanagementsystem.services;

import com.keima.employeemanagementsystem.entity.Bonus;
import com.keima.employeemanagementsystem.entity.Company;
import com.keima.employeemanagementsystem.entity.Employee;
import com.keima.employeemanagementsystem.model.LoginDetails;

import java.util.List;

public interface CompanyService {

    public Company createCompany(Company company);
    public boolean isUserNameAvailable(String userName);
    public void deleteCompany(Long id);
    public Company updateCompany(Company company);
    public List<Employee> getAllEmployees(Long id);
    public Employee getEmployee(Long id);
    public Employee addEmployee(Employee employee, Long id);
    public boolean deleteEmployee(Long companyId, Long employeeId);
    public Company loginCompany(LoginDetails loginDetails);
    public List<Bonus> getBonuses(Long id);
    public Bonus giveBonus(Long id, Bonus bonus) ;
    public boolean deleteBonus(Long employeeId, Long bonusId);
}
