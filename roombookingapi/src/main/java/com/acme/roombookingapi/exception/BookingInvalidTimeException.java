package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class BookingInvalidTimeException extends RuntimeException {

  public final String code = "booking.invalid.time";

  public BookingInvalidTimeException() {
    super(
        "Booking should not be in the past and should have a duration of at least 1 hour or consecutive multiples of 1 hour (2, 3, 4, ...)!");
  }
}
