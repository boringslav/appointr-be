package com.appointr.dto.user;

import com.appointr.repository.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
