package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learning.entities.Reservation;
import com.learning.entities.Employee;
import com.learning.entities.Training;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  boolean existsByEmployeeAndAndTraining(Employee employee, Training training);
}
