package com.learning.web;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.dao.TrainingRepository;
import com.learning.entities.Training;

@RestController
public class CatalogueController {
  private TrainingRepository trainingRepository;

  public CatalogueController(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  @GetMapping(path ="/photoTraining/{id}", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
    Training training = trainingRepository.findById(id).get();

    return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/training/" + training.getPhotoName()));
  }

  @PostMapping(path = "uploadPhoto/{id}")
  public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception {
    Training training = trainingRepository.findById(id).get();

    training.setPhotoName(id + ".png");
    Files.write(Paths.get(System.getProperty("user.home") + "/training/" + training.getPhotoName()), file.getBytes());
    trainingRepository.save(training);
  }
}
