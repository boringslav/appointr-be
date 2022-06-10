package com.appointr.controllers;

import com.appointr.dto.booking.BookingDTO;
import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.dto.booking.GetAllBookingsResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.Booking;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.booking.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"})
    void createBooking_shouldReturn201WithBookingId() throws Exception {
        CreateBookingRequestDTO bookingRequestDTO = CreateBookingRequestDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11").build();
        when(bookingService.createBooking(bookingRequestDTO)).thenReturn(CreateBookingResponseDTO.builder().bookingId(1L).build());

        mockMvc.perform(post("/bookings/new")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                           "bookingId": 1
                        }
                        """));
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"CUSTOMER"})
    void createBooking_shouldReturn403() throws Exception {
        CreateBookingRequestDTO bookingRequestDTO = CreateBookingRequestDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11").build();
        when(bookingService.createBooking(bookingRequestDTO)).thenReturn(CreateBookingResponseDTO.builder().bookingId(1L).build());
        mockMvc.perform(post("/bookings/new")
                .contentType(APPLICATION_JSON_VALUE));
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"})
    void getBookings_shouldReturnBookings_200() throws Exception {
        CreateBookingRequestDTO bookingRequestDTO = CreateBookingRequestDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11").build();
        bookingService.createBooking(bookingRequestDTO);
        List<BookingDTO> bookings = new ArrayList<>();
        bookings.add(BookingDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11")
                .id(1l).build());

        when(bookingService.getAllBookings()).thenReturn(GetAllBookingsResponseDTO.builder().bookings(bookings).build());

        mockMvc.perform(get("/bookings")
                .contentType(APPLICATION_JSON_VALUE));
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"})
    void getBooking_shouldReturnBooking_200() throws Exception {

        CreateBookingRequestDTO bookingRequestDTO = CreateBookingRequestDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11").build();
        bookingService.createBooking(bookingRequestDTO);
        BookingDTO booking = BookingDTO.builder()
                .bookingDate("2022-05-04T10:14:19.392Z")
                .description("A test booking with description")
                .title("Test Booking 11")
                .id(1l).build();

        when(bookingService.getBookingById(1L)).thenReturn(BookingDTO.builder()
                .id(1L)
                .bookingDate(booking.getBookingDate())
                .description(booking.getDescription())
                .title(booking.getTitle())
                .creator(UserDTO.builder().email("borkotest@gmail.com").role(UserRole.COMPANY).build())
                .build());

        mockMvc.perform(get("/bookings")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                           "id": 1,
                           	"title": "Test Booking 11",
                           	"description": "A test booking with description",
                           	"creator": {
                           		"id": 1,
                           		"name": null,
                           		"email": "borkotest@gmail.com",
                           		"role": "COMPANY"
                           	},
                        }
                        """));
    }

}