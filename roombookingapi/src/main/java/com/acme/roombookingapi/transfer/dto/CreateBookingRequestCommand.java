package com.acme.roombookingapi.transfer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequestCommand {
  @NotBlank private String employeeEmail;
  @NotNull private Long roomId;
  @NotNull private LocalDate date;
  @NotNull private LocalTime timeFrom;
  @NotNull private LocalTime timeTo;
}
