package com.acme.roombookingapi.service.impl;

import com.acme.roombookingapi.exception.EmployeeEmailAlreadyExistsException;
import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.repository.EmployeeRepository;
import com.acme.roombookingapi.service.EmployeeService;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public Employee create(CreateEmployeeRequestCommand createEmployeeRequestCommand) {

    validateEmail(createEmployeeRequestCommand.getEmail());

    var newEmployee = new Employee();
    newEmployee.setEmail(createEmployeeRequestCommand.getEmail());
    newEmployee.setName(createEmployeeRequestCommand.getName());
    return employeeRepository.save(newEmployee);
  }

  private void validateEmail(String email) {
    if (employeeRepository.existsByEmail(email)) {
      throw new EmployeeEmailAlreadyExistsException(email);
    }
  }
}
