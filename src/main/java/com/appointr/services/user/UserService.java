package com.appointr.services.user;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;

public interface UserService {
    UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO);
    GetAllUsersResponseDTO getAllUsers();
}
