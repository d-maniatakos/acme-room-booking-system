package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class EmployeeEmailAlreadyExistsException extends RuntimeException {

  public final String code = "employee.email.already.exists";

  public EmployeeEmailAlreadyExistsException(String email) {
    super(String.format("Employee with email: %s already exists!", email));
  }
}
