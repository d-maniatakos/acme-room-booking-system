package com.acme.roombookingapi.exception;

import com.acme.roombookingapi.transfer.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({EmployeeEmailAlreadyExistsException.class})
  public ResponseEntity<Object> handleEmployeeEmailAlreadyExistsException(
      EmployeeEmailAlreadyExistsException exception) {

    var exceptionDto =
        ExceptionDto.builder().code(exception.getCode()).message(exception.getMessage()).build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
  }

  @ExceptionHandler({RoomNameAlreadyExistsException.class})
  public ResponseEntity<Object> handleRoomNameAlreadyExistsException(
      RoomNameAlreadyExistsException exception) {

    var exceptionDto =
        ExceptionDto.builder().code(exception.getCode()).message(exception.getMessage()).build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
  }

  @ExceptionHandler({RoomNotFoundException.class})
  public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException exception) {

    var exceptionDto =
        ExceptionDto.builder().code(exception.getCode()).message(exception.getMessage()).build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
  }

  @ExceptionHandler({EmployeeEmailNotFoundException.class})
  public ResponseEntity<Object> handleEmployeeEmailNotFoundException(
      EmployeeEmailNotFoundException exception) {

    var exceptionDto =
        ExceptionDto.builder().code(exception.getCode()).message(exception.getMessage()).build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
  }

  @ExceptionHandler({BookingNotFoundException.class})
  public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException exception) {

    var exceptionDto =
        ExceptionDto.builder().code(exception.getCode()).message(exception.getMessage()).build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
  }
}
