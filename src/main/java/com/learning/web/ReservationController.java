package com.learning.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dao.EmployeeRepository;
import com.learning.dao.ReservationRepository;
import com.learning.dao.TrainingRepository;
import com.learning.entities.Employee;
import com.learning.entities.Reservation;
import com.learning.entities.Training;

@RestController
public class ReservationController {
  private ReservationRepository reservationRepository;
  private EmployeeRepository employeeRepository;
  private TrainingRepository trainingRepository;

  public ReservationController(ReservationRepository reservationRepository,
                               EmployeeRepository employeeRepository,
                               TrainingRepository trainingRepository
                               ) {
    this.reservationRepository = reservationRepository;
    this.employeeRepository = employeeRepository;
    this.trainingRepository = trainingRepository;
  }


  @PostMapping(path = "saveReservation")
  public void saveReservation(@RequestBody Map<String, Object> payload) {
    Employee employee = employeeRepository.findByUsername(payload.get("username").toString());
    Training training = trainingRepository.findById(Long.parseLong(payload.get("trainingId").toString())).get();

    reservationRepository.save(new Reservation(null, employee, training, LocalDateTime.now()));
  }

  @GetMapping(path ="/checkReservation/{username}/{idTraining}")
  public boolean checkReservation(@PathVariable String username, @PathVariable Long idTraining) {
    Employee employee = employeeRepository.findByUsername(username);
    Training training = trainingRepository.findById(idTraining).get();

    return reservationRepository.existsByEmployeeAndAndTraining(employee, training);
  }
}
