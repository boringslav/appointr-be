package com.appointr.controllers;

import com.appointr.dto.booking.*;
import com.appointr.dto.user.UserDTO;
import com.appointr.repository.entity.Booking;
import com.appointr.services.booking.impl.BookingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingServiceImpl bookingService;

    @GetMapping
    public ResponseEntity<GetAllBookingsResponseDTO> getAllBookings() {
        try {
            GetAllBookingsResponseDTO bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, err.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable(value = "id") final long id) {
        try {
            final BookingDTO foundBooking = bookingService.getBookingById(id);
            return ResponseEntity.ok(foundBooking);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<CreateBookingResponseDTO> createBooking(
            @RequestBody @Valid CreateBookingRequestDTO createBookingRequestDTO) throws ResponseStatusException {
        try {
            CreateBookingResponseDTO response = bookingService.createBooking(createBookingRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, err.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<CreateBookingResponseDTO> updateBooking(
            @PathVariable(value = "id") final long id,
            @RequestBody UpdateBookingDataDTO bookingData) {

        try {
          CreateBookingResponseDTO response =  bookingService.updateBooking(id,bookingData);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, err.getMessage());
        }

    }

}
