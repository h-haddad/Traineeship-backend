package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learning.entities.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  public Employee findByUsername(String username);
}
