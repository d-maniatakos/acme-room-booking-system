package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.RoomService;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import com.acme.roombookingapi.transfer.dto.RoomDto;
import com.acme.roombookingapi.transfer.mapper.RoomMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;
  private final RoomMapper roomMapper;

  @PostMapping
  public RoomDto createRoom(@RequestBody CreateRoomRequestCommand command) {
    var newRoom = roomService.create(command);
    return roomMapper.roomToRoomDto(newRoom);
  }

  @GetMapping
  public List<RoomDto> getAllRooms() {
    var rooms = roomService.getAllRooms();
    return rooms.stream().map(roomMapper::roomToRoomDto).toList();
  }
}
