package com.learning.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private int phoneNumber;
  @Column(unique = true)
  private String username;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private Boolean actived;
  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles = new ArrayList<>();
  @OneToMany(mappedBy = "employee",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<Reservation> reservations = new ArrayList<>();
}
