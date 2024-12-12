package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class BookingInPastException extends RuntimeException {

  public final String code = "booking.in.past";

  public BookingInPastException(Long id) {
    super(String.format("Booking with id: %d cannot be deleted because it is a past booking!", id));
  }

  public BookingInPastException() {
    super("Cannot create a booking in past!");
  }
}
