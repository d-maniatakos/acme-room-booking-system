package com.acme.roombookingapi.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RetrieveBookingsQuery {
  @NotNull
  @Schema(example = "1")
  private Long roomId;

  @NotNull
  @Schema(example = "2024-12-12")
  private LocalDate date;

  @NotNull
  @Schema(example = "0")
  private int page = 0;

  @Schema(example = "100")
  @NotNull
  @Positive
  private int pageSize = 100;
}
