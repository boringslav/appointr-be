package com.appointr.services.user;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public interface UserService {
    UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO);
    GetAllUsersResponseDTO getAllUsers();
    UserDTO getUserById(@NotNull Long id) throws Exception;
    UserDTO getUserByEmail(@Email @NotNull String email) throws Exception;
}
