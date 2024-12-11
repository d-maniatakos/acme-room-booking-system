package com.acme.roombookingapi.service;

import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import java.util.List;

public interface RoomService {
  public Room create(CreateRoomRequestCommand createRoomRequestCommand);

  public List<Room> getAllRooms();
}
