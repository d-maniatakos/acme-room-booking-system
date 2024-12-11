package com.acme.roombookingapi.transfer.dto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDto {
  private Long id;
  private EmployeeDto employee;
  private RoomDto room;
  private LocalDate date;
  private LocalTime timeFrom;
  private LocalTime timeTo;
}
