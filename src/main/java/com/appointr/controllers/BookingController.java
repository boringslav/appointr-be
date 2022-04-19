package com.appointr.controllers;

import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.dto.booking.GetAllBookingsResponseDTO;
import com.appointr.services.booking.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<GetAllBookingsResponseDTO> getAllBookings() {
        try {
            GetAllBookingsResponseDTO bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        }
        catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, err.getMessage());
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

}
