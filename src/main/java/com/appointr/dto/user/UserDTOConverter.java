package com.appointr.dto.user;

import com.appointr.repository.entity.User;

public class UserDTOConverter {

    private UserDTOConverter () {

    }

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
