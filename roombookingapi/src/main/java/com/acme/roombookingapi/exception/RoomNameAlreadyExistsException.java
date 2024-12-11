package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class RoomNameAlreadyExistsException extends RuntimeException {

  public final String code = "room.name.already.exists";

  public RoomNameAlreadyExistsException(String name) {
    super(String.format("Room with name: %s already exists!", name));
  }
}
