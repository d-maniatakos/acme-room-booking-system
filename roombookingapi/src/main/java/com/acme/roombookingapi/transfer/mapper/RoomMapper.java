package com.acme.roombookingapi.transfer.mapper;

import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.transfer.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
  public RoomDto roomToRoomDto(Room room);
}
