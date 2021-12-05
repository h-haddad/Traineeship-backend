package com.learning.service;

import com.learning.entities.Employee;
import com.learning.entities.Role;

public interface AccountService {
  public Employee saveEmployee(String firstName,
                               String lastName,
                               String email,
                               int phoneNumber,
                               String username,
                               String password,
                               String confirmedPassword);
  public Role saveRole(Role role);
  public Employee loadEmployeeByUsername(String username);
  public void addRoleToUser(String username, String roleName);
}
