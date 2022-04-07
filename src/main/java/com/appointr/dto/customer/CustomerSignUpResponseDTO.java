package com.appointr.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignUpResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
