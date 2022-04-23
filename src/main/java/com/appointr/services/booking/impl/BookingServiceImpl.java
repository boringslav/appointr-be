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

import javax.transaction.Transactional;
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

    //TODO - Check if the user who wants to update the booking is its creator
    public CreateBookingResponseDTO updateBooking(Long id, UpdateBookingDataDTO newBookingData) throws Exception {
        Optional<Booking> oldBooking = bookingRepository.findById(id);

        if (oldBooking.isEmpty()) {
            throw new Exception("Booking Not Found");
        }

        Booking booking = oldBooking.get();
        booking.setTitle(newBookingData.getTitle());
        booking.setDescription(newBookingData.getDescription());

        booking = bookingRepository.save(booking);

        return CreateBookingResponseDTO.builder()
                .bookingId(booking.getId()).build();

    }

    /**
     * TODO - Check if the user who wants to update the booking is its creator
     * TODO -
     * @param id
     * @throws Exception
     * could not execute statement; SQL [n/a]; constraint [fk63yud1c4pbg7n9xgtajdqrp3v];
     * nested exception is org.hibernate.exception.ConstraintViolationException:
     * could not execute statement
     */
    @Transactional
    public void deleteBooking(Long id) throws  Exception{
        userRepository.deleteById(id);
    }

}
