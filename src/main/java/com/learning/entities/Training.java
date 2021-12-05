package com.learning.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Transactional
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Training {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String shortDescription;
  @Lob
  private String description;
  private String level;
  private int duration;
  private boolean available;
  private String photoName;
  @ManyToOne
  private Category category;
  @OneToMany(mappedBy = "training",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<Reservation> reservedTraining;
}
