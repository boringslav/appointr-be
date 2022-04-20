package com.appointr.dto.booking;

import com.appointr.dto.user.UserDTOConverter;
import com.appointr.repository.entity.Booking;

public class BookingDTOConverter {

    private BookingDTOConverter() {

    };

    public static BookingDTO convertToDTO(Booking booking) {


        return BookingDTO.builder()
                .id(booking.getId())
                .creator(UserDTOConverter.convertToDTO(booking.getCreator()))
                .customer(booking.getCustomer() == null ? null : UserDTOConverter.convertToDTO(booking.getCustomer()))
                .title(booking.getTitle())
                .description(booking.getDescription())
                .build();
    }
}
