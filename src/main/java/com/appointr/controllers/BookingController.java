package com.appointr.controllers;

import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.services.booking.BookingService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
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

    @PostMapping("/new")
    public ResponseEntity<CreateBookingResponseDTO> createBooking(
            @RequestBody @Valid CreateBookingRequestDTO createBookingRequestDTO) throws AuthenticationException {
        try {
            CreateBookingResponseDTO response = bookingService.createBooking(createBookingRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, err.getMessage());
        }
    }
}
