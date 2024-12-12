package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class BookingConflictException extends RuntimeException {

  public final String code = "booking.conflict";

  public BookingConflictException(Long roomId) {
    super(String.format("Room with id: %d is booked during the requested booking time", roomId));
  }
}
