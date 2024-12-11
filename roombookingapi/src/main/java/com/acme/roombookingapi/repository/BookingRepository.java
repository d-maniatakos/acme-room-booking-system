package com.acme.roombookingapi.repository;

import com.acme.roombookingapi.model.Booking;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  Page<Booking> findAllByRoomIdAndDate(Long roomId, LocalDate date, Pageable pageable);
}
