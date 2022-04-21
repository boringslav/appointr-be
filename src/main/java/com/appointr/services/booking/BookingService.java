package com.appointr.services.booking;

import com.appointr.dto.booking.BookingDTO;
import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.dto.booking.GetAllBookingsResponseDTO;
import org.apache.tomcat.websocket.AuthenticationException;

public interface BookingService {
    CreateBookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) throws AuthenticationException;
    GetAllBookingsResponseDTO getAllBookings();
    BookingDTO getBookingById(Long id) throws Exception;
}
