package com.acme.roombookingapi.repository;

import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.model.Room;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  Page<Booking> findAllByRoomIdAndDate(Long roomId, LocalDate date, Pageable pageable);

  @Query(
      "select case when (count(b) > 0)  then true else false end "
          + "from Booking b "
          + "where b.room = :room "
          + "and b.date = :date "
          + "and ((b.timeFrom between :timeFrom and :timeTo and b.timeFrom != :timeTo) "
          + "or (b.timeTo between :timeFrom and :timeTo and b.timeTo != :timeFrom))")
  boolean overlappingBookingExists(Room room, LocalDate date, LocalTime timeFrom, LocalTime timeTo);
}
