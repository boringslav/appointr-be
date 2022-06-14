package com.appointr.controllers;

import com.appointr.dto.user.*;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.user.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"CUSTOMER"})
    void getAllUsers_shouldBeForbidden_whenRoleIsCustomer() throws Exception {
        when(userService.getAllUsers()).thenReturn(GetAllUsersResponseDTO.builder().build());

        mockMvc.perform(get("/users")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"})
    void getAllUsers_shouldBeForbidden_whenRoleIsCompany() throws Exception {
        when(userService.getAllUsers()).thenReturn(GetAllUsersResponseDTO.builder().build());

        mockMvc.perform(get("/users")).andExpect(status().isForbidden());
    }

    @Test
    @Disabled
    @WithMockUser(username = "borkotest@gmail.com", roles = {"ADMIN"})
    void getAllUsers_shouldBeOK_whenRoleIsAdmin() throws Exception {
        when(userService.getAllUsers()).thenReturn(GetAllUsersResponseDTO.builder().build());

        mockMvc.perform(get("/users").contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"CUSTOMER"})
    void getUserById_shouldBeForbidden_whenRoleIsCustomer() throws Exception {
        when(userService.getUserById(1L)).thenReturn(UserDTO.builder().build());
        mockMvc.perform(get("/users/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"})
    void getUserById_shouldBeForbidden_whenRoleIsCompany() throws Exception {
        when(userService.getUserById(1L)).thenReturn(UserDTO.builder().build());
        mockMvc.perform(get("/users/1")).andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"COMPANY"}, password = "password")
    void signUp_Should_returnCreated_WhenProvidedWithCorrectCredentials() throws Exception {

        when(userService.signUp(UserSignUpRequestDTO.builder()
                .email("borkotest@gmail.com")
                .password("password")
                .role(UserRole.COMPANY)
                .build())).thenReturn(UserSignUpResponseDTO.builder().build());

        mockMvc.perform(post("/users/sign-up")
                        .contentType("application/json")
                        .content("""
                                {
                                	"email": "borkotest@gmail.com",
                                	"password": "password",
                                	"name": "BorkoCorp",
                                	"role": "COMPANY"
                                }
                                """
                ))
                .andExpect(status().isCreated());
    }
    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"CUSTOMER"}, password = "password")
    void signUp_Should_returnBadRequest_WhenNotProvidedWithCorrectCredentials() throws Exception {
        when(userService.signUp(UserSignUpRequestDTO.builder()
                .email("borkotest@gmail.com")
                .password("password")
                .role(UserRole.CUSTOMER)
                .build())).thenReturn(UserSignUpResponseDTO.builder().build());

        mockMvc.perform(post("/users/sign-up")
                        .contentType("application/json")
                        .content("""
                                {
                                	"email": "borkotest@gmail.com",
                                	"password": "password",
                                }
                                """
                        ))
                .andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"CUSTOMER"}, password = "password")
    void updateMe_should_UpdateTheLoggedInUserDetails() throws Exception {
        EditUserDTORequest editUserDTORequest = EditUserDTORequest.builder()
                .email("borkotest1gmail.com")
                .name("borko")
                .password("123123")
                .build();

        when(userService.editMyProfile(editUserDTORequest)).thenReturn(
                UserDTO.builder()
                        .id(1L)
                        .name("borko")
                        .email("borkotest1gmail.com")
                        .role(UserRole.COMPANY)
                        .build()
        );
        mockMvc.perform(put("/users/edit-me")
                        .contentType("application/json")
                        .content("""
                                {
                                	"email": "borkotest1gmail.com",
                                	"password": "password",
                                	"name": "burger",
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void getMyProfile() {
    }
}