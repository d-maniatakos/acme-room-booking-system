package com.acme.roombookingapi.service.impl;

import com.acme.roombookingapi.exception.BookingNotFoundException;
import com.acme.roombookingapi.exception.EmployeeEmailNotFoundException;
import com.acme.roombookingapi.exception.RoomNotFoundException;
import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.repository.BookingRepository;
import com.acme.roombookingapi.repository.EmployeeRepository;
import com.acme.roombookingapi.repository.RoomRepository;
import com.acme.roombookingapi.service.BookingService;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final EmployeeRepository employeeRepository;
  private final RoomRepository roomRepository;
  private final BookingRepository bookingRepository;

  @Override
  public Booking create(CreateBookingRequestCommand createBookingRequestCommand) {

    var employee =
        employeeRepository
            .findByEmailIgnoreCase(createBookingRequestCommand.getEmployeeEmail())
            .orElseThrow(
                () ->
                    new EmployeeEmailNotFoundException(
                        createBookingRequestCommand.getEmployeeEmail()));
    var room =
        roomRepository
            .findById(createBookingRequestCommand.getRoomId())
            .orElseThrow(() -> new RoomNotFoundException(createBookingRequestCommand.getRoomId()));

    var newBooking = new Booking();
    newBooking.setEmployee(employee);
    newBooking.setRoom(room);
    newBooking.setDate(createBookingRequestCommand.getDate());
    newBooking.setTimeFrom(createBookingRequestCommand.getTimeFrom());
    newBooking.setTimeTo(createBookingRequestCommand.getTimeTo());
    return bookingRepository.save(newBooking);
  }

  @Override
  public Page<Booking> getBookings(RetrieveBookingsQuery query) {
    var pageable = PageRequest.of(query.getPage(), query.getPageSize());

    return bookingRepository.findAllByRoomIdAndDate(query.getRoomId(), query.getDate(), pageable);
  }

  public void delete(Long id) {
    var booking =
        bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

    bookingRepository.delete(booking);
  }
}
