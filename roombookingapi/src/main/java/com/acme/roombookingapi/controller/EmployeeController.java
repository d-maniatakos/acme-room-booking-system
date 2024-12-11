package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.EmployeeService;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;
import com.acme.roombookingapi.transfer.dto.EmployeeDto;
import com.acme.roombookingapi.transfer.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @PostMapping
  public EmployeeDto createEmployee(@RequestBody CreateEmployeeRequestCommand command) {
    var newEmployee = employeeService.create(command);
    return employeeMapper.employeeToEmployeeDto(newEmployee);
  }
}
