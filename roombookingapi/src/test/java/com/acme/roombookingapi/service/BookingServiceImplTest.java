package com.acme.roombookingapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.acme.roombookingapi.exception.*;
import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.repository.BookingRepository;
import com.acme.roombookingapi.repository.EmployeeRepository;
import com.acme.roombookingapi.repository.RoomRepository;
import com.acme.roombookingapi.service.impl.BookingServiceImpl;
import com.acme.roombookingapi.transfer.dto.CreateBookingRequestCommand;
import com.acme.roombookingapi.transfer.dto.RetrieveBookingsQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
  @Mock private BookingRepository bookingRepository;

  @Mock private RoomRepository roomRepository;

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private BookingServiceImpl bookingService;

  @Test
  public void givenValidCreateBookingRequestCommand_whenCreate_thenBookingSuccessfullyCreated() {

    when(bookingRepository.save(Mockito.any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

    var room = Room.builder().id(1L).name("Room").build();
    var employee = Employee.builder().id(1L).email("someone@email.com").name("Some One").build();

    when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(employee));
    when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));

    var createBookingRequestCommand =
        CreateBookingRequestCommand.builder()
            .employeeEmail("someone@email.com")
            .roomId(1L)
            .date(LocalDate.parse("2025-01-01"))
            .timeFrom(LocalTime.parse("08:00"))
            .timeTo(LocalTime.parse("10:00"))
            .build();

    var result = bookingService.create(createBookingRequestCommand);

    assertThat(result.getRoom()).isEqualTo(room);
    assertThat(result.getEmployee()).isEqualTo(employee);
    assertThat(result.getDate()).isEqualTo(createBookingRequestCommand.getDate());
    assertThat(result.getTimeFrom()).isEqualTo(createBookingRequestCommand.getTimeFrom());
    assertThat(result.getTimeTo()).isEqualTo(createBookingRequestCommand.getTimeTo());

    verify(bookingRepository, times(1)).save(any(Booking.class));
  }

  @Test
  public void
      givenCreateBookingRequestCommandWithNonExistingEmployee_whenCreate_thenEmployeeEmailNotFoundExceptionThrown() {

    when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

    var createBookingRequestCommand =
        CreateBookingRequestCommand.builder()
            .employeeEmail("someone@email.com")
            .roomId(1L)
            .date(LocalDate.parse("2025-01-01"))
            .timeFrom(LocalTime.parse("08:00"))
            .timeTo(LocalTime.parse("10:00"))
            .build();

    assertThrows(
        EmployeeEmailNotFoundException.class,
        () -> bookingService.create(createBookingRequestCommand));
  }

  @Test
  public void
      givenCreateBookingRequestCommandWithNonExistingRoom_whenCreate_thenRoomNotFoundExceptionThrown() {

    var employee = Employee.builder().id(1L).email("someone@email.com").name("Some One").build();

    when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(employee));
    when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());

    var createBookingRequestCommand =
        CreateBookingRequestCommand.builder()
            .employeeEmail("someone@email.com")
            .roomId(1L)
            .date(LocalDate.parse("2025-01-01"))
            .timeFrom(LocalTime.parse("08:00"))
            .timeTo(LocalTime.parse("10:00"))
            .build();

    assertThrows(
        RoomNotFoundException.class, () -> bookingService.create(createBookingRequestCommand));
  }

  @Test
  public void
      givenCreateBookingRequestCommandWithPastDate_whenCreate_thenBookingInvalidTimeExceptionThrown() {
    var room = Room.builder().id(1L).name("Room").build();
    var employee = Employee.builder().id(1L).email("someone@email.com").name("Some One").build();

    when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(employee));
    when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));

    var createBookingRequestCommand =
        CreateBookingRequestCommand.builder()
            .employeeEmail("someone@email.com")
            .roomId(1L)
            .date(LocalDate.parse("2021-01-01"))
            .timeFrom(LocalTime.parse("08:00"))
            .timeTo(LocalTime.parse("10:00"))
            .build();

    assertThrows(
        BookingInvalidTimeException.class,
        () -> bookingService.create(createBookingRequestCommand));
  }

  @Test
  public void
      givenCreateBookingRequestCommandWithConflict_whenCreate_thenBookingConflictExceptionThrown() {
    var room = Room.builder().id(1L).name("Room").build();
    var employee = Employee.builder().id(1L).email("someone@email.com").name("Some One").build();

    when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(employee));
    when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));
    when(bookingRepository.overlappingBookingExists(
            any(Room.class), any(LocalDate.class), any(LocalTime.class), any(LocalTime.class)))
        .thenReturn(true);

    var createBookingRequestCommand =
        CreateBookingRequestCommand.builder()
            .employeeEmail("someone@email.com")
            .roomId(1L)
            .date(LocalDate.parse("2025-01-01"))
            .timeFrom(LocalTime.parse("08:00"))
            .timeTo(LocalTime.parse("10:00"))
            .build();

    assertThrows(
        BookingConflictException.class, () -> bookingService.create(createBookingRequestCommand));
  }

  @Test
  public void givenValidBookingId_whenCancel_thenBookingSuccessfullyDeleted() {
    var id = 1L;
    var booking =
        Booking.builder()
            .date(LocalDate.parse("2025-01-01"))
            .timeFrom(LocalTime.parse("10:00"))
            .timeTo(LocalTime.parse("11:00"))
            .build();

    when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

    assertDoesNotThrow(() -> bookingService.cancel(id));
  }

  @Test
  public void givenNonExistingBookingId_whenCancel_thenBookingNotFoundExceptionThrown() {
    var id = 1L;

    when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(BookingNotFoundException.class, () -> bookingService.cancel(id));
  }

  @Test
  public void givenRetrieveBookingsQuery_whenGetByQuery_thenPageOfBookingsReturned() {
    var retrieveBookingsQuery =
        RetrieveBookingsQuery.builder()
            .roomId(1L)
            .date(LocalDate.now())
            .page(0)
            .pageSize(10)
            .build();

    var bookings = Mockito.mock(Page.class);
    when(bookingRepository.findAllByRoomIdAndDate(
            anyLong(), any(LocalDate.class), any(Pageable.class)))
        .thenReturn(bookings);

    var result = bookingService.getByQuery(retrieveBookingsQuery);
    assertThat(result).isEqualTo(bookings);
  }
}
