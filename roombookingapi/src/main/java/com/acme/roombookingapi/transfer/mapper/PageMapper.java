package com.acme.roombookingapi.transfer.mapper;

import com.acme.roombookingapi.transfer.dto.PageDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {
  public PageDto pageToPageDto(Page page);
}
