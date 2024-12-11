package com.acme.roombookingapi.exception;

import lombok.Getter;

@Getter
public class EmployeeEmailNotFoundException extends RuntimeException {

  public final String code = "employee.email.not.found";

  public EmployeeEmailNotFoundException(String email) {
    super(String.format("Employee with email: %s not found!", email));
  }
}
