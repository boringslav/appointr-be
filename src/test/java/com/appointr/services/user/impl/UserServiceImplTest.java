package com.appointr.services.user.impl;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest  {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void getAllUsers() {
        UserSignUpRequestDTO signUpDTO = UserSignUpRequestDTO.builder()
                .email("testEmail@gmail.com")
                .name("Test Name")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();


        UserSignUpResponseDTO savedUser = userService.signUp(signUpDTO);
        GetAllUsersResponseDTO foundUsers =  userService.getAllUsers();
        assertEquals(foundUsers.getUsers().size(), 1);
    }

    @Test
    void getUserById() throws Exception {
        UserSignUpRequestDTO signUpDTO = UserSignUpRequestDTO.builder()
                .email("testEmail@gmail.com")
                .name("Test Name")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();


        UserSignUpResponseDTO savedUser = userService.signUp(signUpDTO);
        UserDTO foundUser = userService.getUserById(savedUser.getId());
        assertEquals(foundUser.getId(), savedUser.getId());

    }

    @Test
    void getUserByEmail() throws Exception {
        UserSignUpRequestDTO signUpDTO = UserSignUpRequestDTO.builder()
                .email("testEmail@gmail.com")
                .name("Test Name")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();


        UserSignUpResponseDTO savedUser = userService.signUp(signUpDTO);
        UserDTO foundUser = userService.getUserByEmail(savedUser.getEmail());
        assertEquals(foundUser.getEmail(), savedUser.getEmail());
    }

    @Test
    void signUp() throws Exception {
        UserSignUpRequestDTO signUpDTO = UserSignUpRequestDTO.builder()
                .email("testEmail@gmail.com")
                .name("Test Name")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();


        UserSignUpResponseDTO savedUser = userService.signUp(signUpDTO);
        UserDTO foundUser = userService.getUserByEmail("testEmail@gmail.com");
        assertEquals(foundUser.getEmail(), savedUser.getEmail());

    }

    @Test
    public void loadUserByUsername() throws UsernameNotFoundException {
        UserSignUpRequestDTO signUpDTO = UserSignUpRequestDTO.builder()
                .email("testEmail@gmail.com")
                .name("Test Name")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();

        UserSignUpResponseDTO savedUser = userService.signUp(signUpDTO);

        UserDetails userDetails = userService.loadUserByUsername(signUpDTO.getEmail());
        assertEquals(savedUser.getEmail(), userDetails.getUsername());
    }
}