package com.acme.roombookingapi.transfer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionDto {
  private String code;
  private String message;
}
