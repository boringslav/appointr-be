package com.appointr.dto.user;

import com.appointr.repository.entity.UserRole;
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
public class UserSignUpRequestDTO {
    private String name;

    @NotBlank
    @Email
    @Column(name="email", unique = true)
    private String email;

    @NotBlank
    @Column(name="password")
    private String password;

    @Column(name="role")
    private UserRole role;

}
