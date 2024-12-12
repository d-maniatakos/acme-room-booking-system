package com.acme.roombookingapi.transfer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequestCommand {
  @NotBlank private String name;
  @Email private String email;
}
