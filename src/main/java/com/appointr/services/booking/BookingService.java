package com.appointr.services.booking;

import com.appointr.dto.booking.BookingDTOConverter;
import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.dto.booking.GetAllBookingsResponseDTO;
import com.appointr.repository.BookingRepository;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.Booking;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) throws AuthenticationException {

        Optional<User> foundUser = userRepository.findById(requestDTO.getCreator().getId());

        if(foundUser.isEmpty()) {
            throw new AuthenticationException("User does not exists");
        }
        if(foundUser.get().getRole() == UserRole.CUSTOMER) {
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

}
