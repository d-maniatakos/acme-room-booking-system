package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.EmployeeService;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;
import com.acme.roombookingapi.transfer.dto.EmployeeDto;
import com.acme.roombookingapi.transfer.dto.ExceptionDto;
import com.acme.roombookingapi.transfer.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
@Tag(name = "Employees API")
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @PostMapping
  @Operation(
      summary = "Create an Employee",
      description = "Creates and returns the newly created employee")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully created"),
        @ApiResponse(
            responseCode = "409",
            description = "Employee email already exists",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid parameters given",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class))),
      })
  public EmployeeDto createEmployee(@RequestBody CreateEmployeeRequestCommand command) {
    var newEmployee = employeeService.create(command);
    return employeeMapper.employeeToEmployeeDto(newEmployee);
  }
}
