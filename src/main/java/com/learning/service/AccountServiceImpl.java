package com.learning.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.dao.EmployeeRepository;
import com.learning.dao.RoleRepository;
import com.learning.entities.Employee;
import com.learning.entities.Role;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
  private EmployeeRepository employeeRepository;
  private RoleRepository roleRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public AccountServiceImpl(EmployeeRepository employeeRepository,
                            RoleRepository roleRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.employeeRepository = employeeRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public Employee saveEmployee(String firstName,
                               String lastName,
                               String email,
                               int phoneNumber,
                               String username,
                               String password,
                               String confirmedPassword) {
    Employee employee = employeeRepository.findByUsername(username);

    if(employee != null) {
        throw new RuntimeException("Employee already exists !");
    }
    if(!password.equals(confirmedPassword)) {
        throw new RuntimeException("Please check your password !");
    }

    Employee newEmployee = new Employee();

    newEmployee.setFirstName(firstName);
    newEmployee.setLastName(lastName);
    newEmployee.setEmail(email);
    newEmployee.setPhoneNumber(phoneNumber);
    newEmployee.setUsername(username);
    newEmployee.setPassword(bCryptPasswordEncoder.encode(password));
    newEmployee.setActived(true);
    employeeRepository.save(newEmployee);

    if(!username.equals("admin")) {
      addRoleToUser(username, "USER");
    }

    return newEmployee;
  }

  @Override
  public Role saveRole(Role role) {
      return roleRepository.save(role);
  }

  @Override
  public Employee loadEmployeeByUsername(String username) {
      return employeeRepository.findByUsername(username);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    Employee employee = employeeRepository.findByUsername(username);
    Role role = roleRepository.findByRoleName(roleName);

    employee.getRoles().add(role);
  }
}
