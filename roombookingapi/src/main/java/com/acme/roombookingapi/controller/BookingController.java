package com.acme.roombookingapi.controller;

import com.acme.roombookingapi.service.BookingService;
import com.acme.roombookingapi.transfer.dto.*;
import com.acme.roombookingapi.transfer.mapper.BookingMapper;
import com.acme.roombookingapi.transfer.mapper.PageMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings API")
public class BookingController {

  private final BookingService bookingService;
  private final BookingMapper bookingMapper;
  private final PageMapper pageMapper;

  @PostMapping
  @Operation(
      summary = "Create a Booking",
      description = "Creates and returns the newly created booking (use case #2)")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully created"),
        @ApiResponse(
            responseCode = "404",
            description = "Given employee or room does not exist",
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
  public BookingDto createBooking(@RequestBody CreateBookingRequestCommand command) {
    var newBooking = bookingService.create(command);
    return bookingMapper.bookingToBookingDto(newBooking);
  }

  @GetMapping
  @Operation(
      summary = "Get Bookings by Room and Date",
      description =
          "Returns a page of bookings based on the room and date query parameters (use case #1)")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully fetched"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid parameters given",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class))),
      })
  public PageDto getBookings(RetrieveBookingsQuery query) {
    var bookings = bookingService.getByQuery(query);
    return pageMapper.pageToPageDto(bookings.map(bookingMapper::bookingToBookingDto));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Cancel a Booking",
      description = "Cancels (deletes) a booking given its id (use case #3)")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully cancelled"),
        @ApiResponse(
            responseCode = "404",
            description = "Booking does not exist",
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
  public void cancelBooking(@PathVariable Long id) {
    bookingService.cancel(id);
  }
}
