package com.keima.employeemanagementsystem.api;

import com.keima.employeemanagementsystem.entity.Bonus;
import com.keima.employeemanagementsystem.entity.Company;
import com.keima.employeemanagementsystem.entity.Employee;
import com.keima.employeemanagementsystem.model.CompanyModel;
import com.keima.employeemanagementsystem.model.EmployeeModel;
import com.keima.employeemanagementsystem.model.LoginDetails;
import com.keima.employeemanagementsystem.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Company")
@AllArgsConstructor
public class CompanyRest {

    public final CompanyService companyService;

    @PostMapping("/login")
    public Company login(@RequestBody LoginDetails loginDetails) {
        return companyService.loginCompany(loginDetails);
    }

    @PostMapping("/signUp")
    public Company signIn(@RequestBody CompanyModel companyModel) {
        return companyService.createCompany(Company.fromModel(companyModel));
    }

    @GetMapping("/userNameTaken")
    public boolean isUserNameTaken(@RequestParam String username) {
        return companyService.isUserNameAvailable(username);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(@RequestParam Long id) {
        return companyService.getAllEmployees(id);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return companyService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody EmployeeModel employee, @RequestParam Long id) {
        return companyService.addEmployee(Employee.fromModel(employee), id);
    }

    @DeleteMapping("/employees")
    public boolean deleteEmployee(@RequestParam Long companyId, @RequestParam Long employeeId) {
        return companyService.deleteEmployee(companyId, employeeId);
    }

    @GetMapping("/employees/bonus")
    public List<Bonus> getBonuses(@RequestParam Long id) {
        return companyService.getBonuses(id);
    }

    @PostMapping("/employees/bonus")
    public Bonus addBonus(@RequestBody Bonus bonus, @RequestParam Long id) {
        return companyService.giveBonus(id, bonus);
    }

    @DeleteMapping("/employees/bonus")
    public boolean deleteBonus(@RequestParam Long employeeId, @RequestParam Long bonusId) {
        return companyService.deleteBonus(employeeId, bonusId);
    }
}
