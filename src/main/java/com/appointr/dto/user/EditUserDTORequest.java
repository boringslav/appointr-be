package com.appointr.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDTORequest {
    @Email
    @Column(name="email", unique = true)
    private String email;

    @Length(min=6, max=20)
    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;
}
