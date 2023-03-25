package com.keima.employeemanagementsystem.services.impl;

import com.keima.employeemanagementsystem.entity.Bonus;
import com.keima.employeemanagementsystem.entity.Company;
import com.keima.employeemanagementsystem.entity.Employee;
import com.keima.employeemanagementsystem.exceptionHandler.exception.EntityFindingException;
import com.keima.employeemanagementsystem.exceptionHandler.exception.LoginException;
import com.keima.employeemanagementsystem.model.LoginDetails;
import com.keima.employeemanagementsystem.repository.BonusRepository;
import com.keima.employeemanagementsystem.repository.CompanyRepository;
import com.keima.employeemanagementsystem.repository.EmployeeRepository;
import com.keima.employeemanagementsystem.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    public final CompanyRepository companyRepository;
    public final EmployeeRepository employeeRepository;
    public final BonusRepository bonusRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean isUserNameAvailable(String email) {
        return companyRepository.findFirstByEmail(email).isEmpty();
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Employee> getAllEmployees(Long id) {
        var company = companyRepository.findById(id);
        return company.map(Company::getEmployees).orElse(Collections.emptyList());
    }

    @Override
    public Employee getEmployee(Long id) {
        var employeeData = employeeRepository.findById(id);
        if(employeeData.isPresent()) {
            return employeeData.get();
        }
        throw new EntityFindingException("No employee with this id");
    }

    @Override
    public Employee addEmployee(Employee employee, Long id) {
        var companyDetails = companyRepository.findById(id);
        if(companyDetails.isPresent()) {
            var company = companyDetails.get();
            var employeeData = employeeRepository.save(employee);
            company.addEmployee(employeeData);
            companyRepository.save(company);
            return employeeData;
        }
        throw new EntityFindingException("Company Not Found");
    }

    @Override
    public boolean deleteEmployee(Long companyId, Long employeeId) {
        var companyData = companyRepository.findById(companyId);
        if(companyData.isPresent()) {
            var company = companyData.get();
            company.removeEmployee(employeeId);
            companyRepository.save(company);
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    @Override
    public Company loginCompany(LoginDetails loginDetails) {
        var companyDetail = companyRepository.findFirstByEmail(loginDetails.getEmail());
        if(companyDetail.isPresent()) {
            var company = companyDetail.get();
            log.info(company.toString());
            if(company.getPassword().equals(loginDetails.getPassword())) {
                return company;
            }
            throw new LoginException("Wrong password");
        }
        throw new LoginException("User Id not found");
    }

    @Override
    public List<Bonus> getBonuses(Long id) {
        var employeeDetail = employeeRepository.findById(id);
        return employeeDetail.map(Employee::getBonuses).orElse(Collections.emptyList());
    }

    @Override
    public Bonus giveBonus(Long id, Bonus bonus) {
        var employeeDetails = employeeRepository.findById(id);
        if(employeeDetails.isPresent()) {
            var employee = employeeDetails.get();
            var bonusData = bonusRepository.save(bonus);
            employee.addBonus(bonus);
            employeeRepository.save(employee);
            return bonusData;
        }
        throw new EntityFindingException("Employee not found");
    }

    @Override
    public boolean deleteBonus(Long employeeId, Long bonusId) {
        var employeeData = employeeRepository.findById(employeeId);
        if(employeeData.isPresent()) {
            var employee = employeeData.get();
            employee.removeBonus(bonusId);
            employeeRepository.save(employee);
            bonusRepository.deleteById(bonusId);
            return true;
        }
        return false;
    }
}
