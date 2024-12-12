package com.acme.roombookingapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.acme.roombookingapi.exception.EmployeeEmailAlreadyExistsException;
import com.acme.roombookingapi.model.Employee;
import com.acme.roombookingapi.repository.EmployeeRepository;
import com.acme.roombookingapi.service.impl.EmployeeServiceImpl;
import com.acme.roombookingapi.transfer.dto.CreateEmployeeRequestCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeServiceImpl employeeService;

  @Test
  public void givenValidCreateEmployeeRequestCommand_whenCreate_thenEmployeeSuccessfullyCreated() {
    when(employeeRepository.existsByEmail(anyString())).thenReturn(false);
    when(employeeRepository.save(Mockito.any(Employee.class))).thenAnswer(i -> i.getArguments()[0]);

    var createEmployeeRequestCommand =
        CreateEmployeeRequestCommand.builder().email("somemail@mail.com").name("Some Name").build();

    var result = employeeService.create(createEmployeeRequestCommand);

    assertThat(result.getEmail()).isEqualTo(createEmployeeRequestCommand.getEmail());
    assertThat(result.getName()).isEqualTo(createEmployeeRequestCommand.getName());

    verify(employeeRepository, times(1)).save(any(Employee.class));
  }

  @Test
  public void
      givenCreateEmployeeRequestCommandWithAlreadyExistingEmail_whenCreate_thenEmailAlreadyExistsExceptionThrown() {
    var createEmployeeRequestCommand =
        CreateEmployeeRequestCommand.builder().email("somemail@mail.com").name("Some Name").build();

    when(employeeRepository.existsByEmail(anyString())).thenReturn(true);

    assertThrows(
        EmployeeEmailAlreadyExistsException.class,
        () -> employeeService.create(createEmployeeRequestCommand));
  }
}
