package com.acme.roombookingapi.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateEmployeeRequestCommand {
  @NotBlank
  @Schema(example = "Someone Someone")
  private String name;

  @Email
  @Schema(example = "someone@acme.com")
  private String email;
}
