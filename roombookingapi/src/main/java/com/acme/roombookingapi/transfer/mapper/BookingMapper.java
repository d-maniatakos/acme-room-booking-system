package com.acme.roombookingapi.transfer.mapper;

import com.acme.roombookingapi.model.Booking;
import com.acme.roombookingapi.transfer.dto.BookingDto;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {EmployeeMapper.class, RoomMapper.class})
public interface BookingMapper {
  public BookingDto bookingToBookingDto(Booking booking);
}
