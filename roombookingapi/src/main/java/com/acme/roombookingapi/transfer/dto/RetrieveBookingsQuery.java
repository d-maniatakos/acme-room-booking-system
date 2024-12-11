package com.acme.roombookingapi.transfer.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveBookingsQuery {
  private Long roomId;
  private LocalDate date;
  private int page = 0;
  private int pageSize = 100;
}
