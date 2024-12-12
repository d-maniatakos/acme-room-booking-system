package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface EmployeeService {
  public Employee create(@Valid CreateEmployeeRequestCommand createEmployeeRequestCommand);
}
