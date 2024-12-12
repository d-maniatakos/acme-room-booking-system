package com.acme.roombookingapi.transfer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
  private Long id;
  private String name;
  private String email;
}
