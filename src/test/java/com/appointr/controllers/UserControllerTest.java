package com.appointr.controllers;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.repository.entity.User;
import com.appointr.services.user.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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

    @Disabled
    @Test
    @WithMockUser(username = "borkotest@gmail.com", roles = {"ADMIN"})
    void getAllUsers_shouldBeOK_whenRoleIsAdmin() throws Exception {
        when(userService.getAllUsers()).thenReturn(GetAllUsersResponseDTO.builder().build());

        mockMvc.perform(get("/users")).andExpect(status().isOk());
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
    void signUp() {
    }

    @Test
    void updateMe() {
    }

    @Test
    void getMyProfile() {
    }
}