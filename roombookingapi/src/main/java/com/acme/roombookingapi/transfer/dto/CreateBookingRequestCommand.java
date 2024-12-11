package com.acme.roombookingapi.transfer.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequestCommand {
  private String employeeEmail;
  private Long roomId;
  private LocalDate date;
  private LocalTime timeFrom;
  private LocalTime timeTo;
}
