package com.acme.roombookingapi.transfer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequestCommand {
  private String name;
}
