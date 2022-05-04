package com.appointr.services.booking.impl;

import com.appointr.dto.booking.*;
import com.appointr.dto.user.UserDTOConverter;
import com.appointr.repository.BookingRepository;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.Booking;
import com.appointr.repository.entity.User;
import com.appointr.services.booking.BookingService;
import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) throws AuthenticationException {

        String loggedInUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByEmail(loggedInUserEmail);

        Booking newBooking = Booking.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .creator(foundUser)
                .bookingDate(requestDTO.getBookingDate())
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

    public CreateBookingResponseDTO updateBooking(Long id, UpdateBookingRequestDTO newBookingData) throws Exception {
        Optional<Booking> oldBooking = bookingRepository.findById(id);

        String loggedInUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (oldBooking.isEmpty()) {
            throw new Exception("Booking Not Found");
        }

        if (!loggedInUserEmail.equals(oldBooking.get().getCreator().getEmail())) {
            throw new Exception("You are not the creator of the booking!");
        }


        Booking booking = oldBooking.get();
        booking.setTitle(newBookingData.getTitle());
        booking.setDescription(newBookingData.getDescription());

        booking = bookingRepository.save(booking);

        return CreateBookingResponseDTO.builder()
                .bookingId(booking.getId()).build();

    }

    @Transactional
    public DeleteBookingResponseDTO deleteBooking(Long id) throws Exception {
        Optional<Booking> foundBooking = bookingRepository.findById(id);
        String loggedInUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (foundBooking.isEmpty()) {
            throw new Exception("Booking not found!");
        }

        if (!loggedInUserEmail.equals(foundBooking.get().getCreator().getEmail())) {
            throw new Exception("You are not the creator of the Booking!");
        }

        userRepository.deleteById(id);

        return DeleteBookingResponseDTO.builder()
                .id((Long) id)
                .build();
    }

    @Transactional
    public BookBookingResponseDTO book(Long id) throws Exception {
        Optional<Booking> foundBooking = bookingRepository.findById(id);
        String loggedUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User customer = userRepository.findUserByEmail(loggedUserEmail);

        if(foundBooking.isEmpty()){
            throw new Exception("Booking not found!");
        }

        Booking booking = foundBooking.get();
        booking.setCustomer(customer);

        return BookBookingResponseDTO.builder().customerId(customer.getId()).build();
    }

}
