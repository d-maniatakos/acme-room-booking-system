package com.acme.roombookingapi.transfer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequestCommand {
  @NotBlank private String name;
}
