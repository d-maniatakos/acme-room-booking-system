package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;

public interface EmployeeService {
  public Employee create(CreateEmployeeRequestCommand createEmployeeRequestCommand);
}
