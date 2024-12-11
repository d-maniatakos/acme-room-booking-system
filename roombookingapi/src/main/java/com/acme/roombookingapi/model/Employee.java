package com.acme.roombookingapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEES")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "NAME", nullable = false)
  private String name;
}
