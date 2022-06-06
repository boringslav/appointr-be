package com.appointr.services.user;

import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.repository.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {
    @Autowired
    private  UserService userService;

//    @Test
//    void signUp() {
//        UserSignUpRequestDTO requestDTO = UserSignUpRequestDTO.builder()
//                .email("testEmail@gmail.com")
//                .name("Test Name")
//                .role(UserRole.ADMIN)
//                .password("123456")
//                .build();
//
//
//
//    }
//
//    @Test
//    void getAllUsers() {
//    }
}