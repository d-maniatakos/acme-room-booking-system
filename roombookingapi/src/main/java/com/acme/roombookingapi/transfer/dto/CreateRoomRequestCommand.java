package com.acme.roombookingapi.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRoomRequestCommand {
  @NotBlank
  @Schema(example = "Some Room")
  private String name;
}
