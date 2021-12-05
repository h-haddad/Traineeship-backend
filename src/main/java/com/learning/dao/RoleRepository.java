package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learning.entities.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
  public Role findByRoleName(String roleName);
}
