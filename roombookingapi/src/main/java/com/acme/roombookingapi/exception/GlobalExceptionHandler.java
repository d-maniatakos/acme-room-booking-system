package com.acme.roombookingapi.exception;

import com.acme.roombookingapi.transfer.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.stream.Collectors;
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

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException exception) {

    var errors = new HashMap<>();
    exception
        .getConstraintViolations()
        .forEach(
            violation -> {
              var errorPath = violation.getPropertyPath();

              // don't expose full property path to error message
              var errorField =
                  errorPath.toString().substring(errorPath.toString().lastIndexOf('.') + 1);

              errors.put(errorField, violation.getMessage());
            });

    var message =
        errors.entrySet().stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue())
            .collect(Collectors.joining(", "));

    var exceptionDto =
        ExceptionDto.builder().code("constraint.violation.exception").message(message).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
  }
}
