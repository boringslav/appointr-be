package com.appointr.services.booking.impl;

import com.appointr.dto.booking.*;
import com.appointr.dto.user.UserDTOConverter;
import com.appointr.repository.BookingRepository;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.Booking;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.booking.BookingService;
import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) throws AuthenticationException {

        Optional<User> foundUser = userRepository.findById(requestDTO.getCreator().getId());

        if (foundUser.isEmpty()) {
            throw new AuthenticationException("User does not exists");
        }
        if (foundUser.get().getRole() == UserRole.CUSTOMER) {
            throw new AuthenticationException("You don`t have permissions to do that!");
        }

        Booking newBooking = Booking.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .creator(foundUser.get())
                .build();

        Booking savedBooking = bookingRepository.save(newBooking);
        return CreateBookingResponseDTO.builder()
                .bookingId(savedBooking.getId()).build();
    }

    public GetAllBookingsResponseDTO getAllBookings() {
        Iterable<Booking> results = bookingRepository.findAll();

        final GetAllBookingsResponseDTO response = new GetAllBookingsResponseDTO();
        response.setBookings(Streams.stream(results)
                .map(BookingDTOConverter::convertToDTO)
                .collect(Collectors.toList()));

        return response;
    }

    public BookingDTO getBookingById(Long id) throws Exception {
        Optional<Booking> foundBooking = bookingRepository.findById(id);

        if (foundBooking.isEmpty()) {
            throw new Exception("Booking Not Found");
        }

        Booking booking = foundBooking.get();

        final BookingDTO response = BookingDTO.builder()
                .id(booking.getId())
                .title(booking.getTitle())
                .description(booking.getDescription())
                .creator(UserDTOConverter.convertToDTO(booking.getCreator()))
                .customer(UserDTOConverter.convertToDTO(booking.getCreator()))
                .build();

        return response;
    }

}
