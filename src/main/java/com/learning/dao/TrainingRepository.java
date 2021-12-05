package com.learning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.learning.entities.Training;

@RepositoryRestResource
public interface TrainingRepository extends JpaRepository<Training, Long> {
  List<Training> findByTitle(String name);

  @RestResource(path="/availableTraining")
  List<Training> findByAvailableIsTrue();
}
