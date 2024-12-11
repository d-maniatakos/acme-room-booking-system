package com.acme.roombookingapi.repository;

import com.acme.roombookingapi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
  boolean existsByName(String name);
}
