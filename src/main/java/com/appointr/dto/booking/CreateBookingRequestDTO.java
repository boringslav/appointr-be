package com.appointr.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequestDTO {
    @NotBlank
    @Length(min = 2, max = 20)
    private String title;

    @NotBlank
    @Length(min = 10, max = 50)
    private String description;

    private String bookingDate;
}
