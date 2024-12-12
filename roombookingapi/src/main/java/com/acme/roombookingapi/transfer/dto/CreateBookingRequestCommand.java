package com.acme.roombookingapi.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBookingRequestCommand {
  @NotBlank
  @Schema(example = "someone@acme.com")
  private String employeeEmail;

  @NotNull
  @Schema(example = "1")
  private Long roomId;

  @NotNull
  @Schema(example = "2025-12-12")
  private LocalDate date;

  @NotNull
  @Schema(example = "10:00")
  private LocalTime timeFrom;

  @NotNull
  @Schema(example = "12:00")
  private LocalTime timeTo;
}
