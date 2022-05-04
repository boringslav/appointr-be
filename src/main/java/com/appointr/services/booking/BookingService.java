package com.appointr.services.booking;

import com.appointr.dto.booking.*;
import org.apache.tomcat.websocket.AuthenticationException;

public interface BookingService {
    CreateBookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) throws AuthenticationException;
    GetAllBookingsResponseDTO getAllBookings();
    BookingDTO getBookingById(Long id) throws Exception;
    CreateBookingResponseDTO updateBooking(Long id, UpdateBookingRequestDTO updateBookingDataDTO) throws Exception;
    DeleteBookingResponseDTO deleteBooking(Long id) throws Exception;
    BookBookingResponseDTO book(Long id) throws Exception;
}
