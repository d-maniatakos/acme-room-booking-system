package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.RoomService;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import com.acme.roombookingapi.transfer.dto.ExceptionDto;
import com.acme.roombookingapi.transfer.dto.RoomDto;
import com.acme.roombookingapi.transfer.mapper.RoomMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
@Tag(name = "Rooms API")
public class RoomController {

  private final RoomService roomService;
  private final RoomMapper roomMapper;

  @PostMapping
  @Operation(summary = "Create a Room", description = "Creates and returns the newly created room")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully created"),
        @ApiResponse(
            responseCode = "409",
            description = "Room name already exists",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid parameters given",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class))),
      })
  public RoomDto createRoom(@RequestBody CreateRoomRequestCommand command) {
    var newRoom = roomService.create(command);
    return roomMapper.roomToRoomDto(newRoom);
  }

  @GetMapping
  @Operation(
      summary = "Get all Rooms",
      description = "Returns an array of all rooms for the predefined list")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully fetched")})
  public List<RoomDto> getAllRooms() {
    var rooms = roomService.getAll();
    return rooms.stream().map(roomMapper::roomToRoomDto).toList();
  }
}
