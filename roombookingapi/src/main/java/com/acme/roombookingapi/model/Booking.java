package com.acme.roombookingapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOKINGS")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "ROOM_ID", nullable = false)
  private Room room;

  @Column(name = "DATE", nullable = false)
  private LocalDate date;

  @Column(name = "TIME_FROM", nullable = false)
  private LocalTime timeFrom;

  @Column(name = "TIME_TO", nullable = false)
  private LocalTime timeTo;
}
