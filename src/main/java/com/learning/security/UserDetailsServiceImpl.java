package com.learning.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entities.Employee;
import com.learning.service.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private AccountService accountService;

  public UserDetailsServiceImpl(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Employee employee = accountService.loadEmployeeByUsername(username);

    if(employee == null) {
      throw new UsernameNotFoundException("invalid employee");
    }

    Collection<GrantedAuthority> authorities = new ArrayList<>();

    employee.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority((role.getRoleName())));
    });

    return new User(employee.getUsername(), employee.getPassword(), authorities);
  }
}
