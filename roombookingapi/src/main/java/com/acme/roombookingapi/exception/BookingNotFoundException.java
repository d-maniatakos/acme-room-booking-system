package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class BookingNotFoundException extends RuntimeException {

  public final String code = "booking.not.found";

  public BookingNotFoundException(Long id) {
    super(String.format("Booking with id: %d not found!", id));
  }
}
