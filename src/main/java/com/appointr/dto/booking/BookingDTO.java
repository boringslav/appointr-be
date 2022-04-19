package com.appointr.dto.booking;

import com.appointr.dto.user.UserDTO;
import com.appointr.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO creator;
    private UserDTO customer;
}
