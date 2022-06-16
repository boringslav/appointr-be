package com.appointr.services.user;

import com.appointr.dto.user.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public interface UserService {
    UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO);
    GetAllUsersResponseDTO getAllUsers();
    UserDTO getUserById(@NotNull Long id) throws Exception;
    UserDTO getUserByEmail(@Email @NotNull String email) throws Exception;
    UserDTO editMyProfile(EditUserDTORequest newData) throws Exception;
    UserDTO getMyProfile() throws Exception;
    DeleteUserResponseDTO deleteMyProfile() throws Exception;
}
