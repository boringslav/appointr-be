package com.appointr.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignUpRequestDTO {
    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "firstName")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "lastName")
    private String lastName;

    @NotBlank
    @Email
    @Column(name="email", unique = true)
    private String email;

    @NotBlank
    @Length(min=6, max=20)
    @Column(name="password")
    private String password;
}
