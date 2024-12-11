package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import org.springframework.data.domain.Page;

public interface BookingService {
  public Booking create(CreateBookingRequestCommand createRoomRequestCommand);

  public Page<Booking> getBookings(RetrieveBookingsQuery query);

  public void delete(Long id);
}
