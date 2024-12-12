package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RoomService {
  public Room create(@Valid CreateRoomRequestCommand createRoomRequestCommand);

  public List<Room> getAllRooms();
}
