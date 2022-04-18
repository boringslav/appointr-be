package com.appointr.dto.user;

import com.appointr.repository.entity.User;

public class UserDTOConverter {
    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
