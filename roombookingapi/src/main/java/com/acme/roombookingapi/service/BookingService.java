package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BookingService {
  public Booking create(@Valid CreateBookingRequestCommand createRoomRequestCommand);

  public Page<Booking> getByQuery(@Valid RetrieveBookingsQuery query);

  public void cancel(@NotNull Long id);
}
