package com.acme.roombookingapi.service.impl;

import com.acme.roombookingapi.exception.RoomNameAlreadyExistsException;
import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.repository.RoomRepository;
import com.acme.roombookingapi.service.RoomService;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

  private final RoomRepository roomRepository;

  @Override
  public Room create(CreateRoomRequestCommand createRoomRequestCommand) {

    validateName(createRoomRequestCommand.getName());

    var newRoom = new Room();
    newRoom.setName(createRoomRequestCommand.getName());
    return roomRepository.save(newRoom);
  }

  @Override
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  private void validateName(String name) {
    if (roomRepository.existsByName(name)) {
      throw new RoomNameAlreadyExistsException(name);
    }
  }
}
