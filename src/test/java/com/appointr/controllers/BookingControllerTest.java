package com.appointr.controllers;

import com.appointr.dto.booking.CreateBookingRequestDTO;
import com.appointr.dto.booking.CreateBookingResponseDTO;
import com.appointr.services.booking.BookingService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

}