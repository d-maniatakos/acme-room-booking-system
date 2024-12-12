package com.acme.roombookingapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.acme.roombookingapi.exception.RoomNameAlreadyExistsException;
import com.acme.roombookingapi.model.Room;
import com.acme.roombookingapi.repository.RoomRepository;
import com.acme.roombookingapi.service.impl.RoomServiceImpl;
import com.acme.roombookingapi.transfer.dto.CreateRoomRequestCommand;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {
  @Mock private RoomRepository roomRepository;

  @InjectMocks private RoomServiceImpl roomService;

  @Test
  public void givenValidCreateRoomRequestCommand_whenCreate_thenRoomSuccessfullyCreated() {

    when(roomRepository.save(Mockito.any(Room.class))).thenAnswer(i -> i.getArguments()[0]);

    var createRoomRequestCommand = CreateRoomRequestCommand.builder().name("Room").build();

    var result = roomService.create(createRoomRequestCommand);

    assertThat(result.getName()).isEqualTo(createRoomRequestCommand.getName());

    verify(roomRepository, times(1)).save(any(Room.class));
  }

  @Test
  public void
      givenCreateRoomRequestCommandWithAlreadyExistingName_whenCreate_thenRooomNameAlreadyExistsExceptionThrown() {
    var createRoomRequestCommand = CreateRoomRequestCommand.builder().name("Room Name").build();

    when(roomRepository.existsByName(anyString())).thenReturn(true);

    assertThrows(
        RoomNameAlreadyExistsException.class, () -> roomService.create(createRoomRequestCommand));
  }

  @Test
  public void givenCreateRoomRequestCommand_whenGetAll_thenListOfRoomsSuccessfullyReturned() {

    var room1 = Room.builder().id(1L).name("Room 1").build();
    var room2 = Room.builder().id(2L).name("Room 2").build();

    var listOfRooms = List.of(room1, room2);

    when(roomRepository.findAll()).thenReturn(listOfRooms);

    var result = roomService.getAll();

    assertThat(result).isEqualTo(listOfRooms);
  }
}
