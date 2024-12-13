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

  @Schema(example = "0")
  private Integer page;

  @Schema(example = "100")
  @Positive
  private Integer pageSize;
}
