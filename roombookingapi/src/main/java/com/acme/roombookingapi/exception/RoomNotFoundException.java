package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class RoomNotFoundException extends RuntimeException {

  public final String code = "room.not.found";

  public RoomNotFoundException(Long id) {
    super(String.format("Room with id: %d not found!", id));
  }
}
