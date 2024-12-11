package com.acme.roombookingapi.transfer.mapper;

import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.transfer.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
  public EmployeeDto employeeToEmployeeDto(Employee employee);
}
