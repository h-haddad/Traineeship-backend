package com.learning.web;

import lombok.Data;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entities.Employee;
import com.learning.service.AccountService;

@RestController
public class EmployeeController {
  private AccountService accountService;

  public EmployeeController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/register")
  public Employee register(@RequestBody EmployeeForm employeeForm) {
    return accountService.saveEmployee(employeeForm.getFirstName(),
                                       employeeForm.getLastName(),
                                       employeeForm.getEmail(),
                                       employeeForm.getPhoneNumber(),
                                       employeeForm.getUsername(),
                                       employeeForm.getPassword(),
                                       employeeForm.getConfirmedPassword());
  }
}

@Data
class EmployeeForm {
  private String firstName;
  private String lastName;
  private String email;
  private int phoneNumber;
  private String username;
  private String password;
  private String confirmedPassword;
}
