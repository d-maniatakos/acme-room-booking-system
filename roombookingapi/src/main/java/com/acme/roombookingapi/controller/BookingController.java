package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.BookingService;
import com.acme.roombookingapi.transfer.dto.BookingDto;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import com.acme.roombookingapi.transfer.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;
  private final BookingMapper bookingMapper;

  @PostMapping
  public BookingDto createBooking(@RequestBody CreateBookingRequestCommand command) {
    var newBooking = bookingService.create(command);
    return bookingMapper.bookingToBookingDto(newBooking);
  }

  @GetMapping
  public Page<BookingDto> getBookings(RetrieveBookingsQuery query) {
    var bookings = bookingService.getBookings(query);
    return bookings.map(bookingMapper::bookingToBookingDto);
  }

  @DeleteMapping("/{id}")
  public void deleteBooking(@PathVariable Long id) {
    bookingService.delete(id);
  }
}
