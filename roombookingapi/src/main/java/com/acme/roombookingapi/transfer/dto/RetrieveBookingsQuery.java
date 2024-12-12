package com.acme.roombookingapi.transfer.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveBookingsQuery {
  @NotNull private Long roomId;
  @NotNull private LocalDate date;
  @NotNull private int page = 0;
  @NotNull private int pageSize = 100;
}
