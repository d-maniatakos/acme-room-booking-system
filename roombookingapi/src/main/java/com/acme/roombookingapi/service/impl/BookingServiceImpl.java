package com.acme.roombookingapi.service.impl;

import com.acme.roombookingapi.exception.*;
import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.repository.BookingRepository;
import com.acme.roombookingapi.repository.EmployeeRepository;
import com.acme.roombookingapi.repository.RoomRepository;
import com.acme.roombookingapi.service.BookingService;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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

    // Employees can book a room for at least 1 hour or consecutive multiples of 1 hour (2, 3, 4,
    // ...)
    if (!isTimeValid(
        createBookingRequestCommand.getDate(),
        createBookingRequestCommand.getTimeFrom(),
        createBookingRequestCommand.getTimeTo())) {
      throw new BookingInvalidTimeException();
    }

    if (bookingRepository.overlappingBookingExists(
        room,
        createBookingRequestCommand.getDate(),
        createBookingRequestCommand.getTimeFrom(),
        createBookingRequestCommand.getTimeTo())) {
      throw new BookingConflictException(room.getId());
    }

    var newBooking = new Booking();
    newBooking.setEmployee(employee);
    newBooking.setRoom(room);
    newBooking.setDate(createBookingRequestCommand.getDate());
    newBooking.setTimeFrom(createBookingRequestCommand.getTimeFrom());
    newBooking.setTimeTo(createBookingRequestCommand.getTimeTo());

    return bookingRepository.save(newBooking);
  }

  @Override
  public Page<Booking> getByQuery(RetrieveBookingsQuery query) {

    var page = Objects.nonNull(query.getPage()) ? query.getPage() : 0;
    var pageSize = Objects.nonNull(query.getPageSize()) ? query.getPageSize() : 100;

    var pageable = PageRequest.of(page, pageSize);

    return bookingRepository.findAllByRoomIdAndDate(query.getRoomId(), query.getDate(), pageable);
  }

  public void cancel(Long id) {
    var booking =
        bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

    // Can’t cancel a booking in the past
    if (isBookingInPast(booking)) {
      throw new BookingInPastException(booking.getId());
    }

    bookingRepository.delete(booking);
  }

  private boolean isBookingInPast(Booking booking) {
    return isBookingInPast(booking.getDate(), booking.getTimeTo());
  }

  private boolean isBookingInPast(LocalDate date, LocalTime timeTo) {
    var bookingEndDateTime = LocalDateTime.of(date, timeTo);
    var currentDateTime = LocalDateTime.now();

    return bookingEndDateTime.isBefore(currentDateTime);
  }

  private boolean isTimeValid(LocalDate date, LocalTime timeFrom, LocalTime timeTo) {

    var durationInMinutes = ChronoUnit.MINUTES.between(timeFrom, timeTo);

    return timeFrom.isBefore(timeTo)
        && durationInMinutes >= 60
        && durationInMinutes % 60 == 0
        && !isBookingInPast(date, timeTo);
  }
}
