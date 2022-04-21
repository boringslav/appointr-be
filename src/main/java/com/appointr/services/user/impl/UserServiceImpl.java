package com.appointr.services.user.impl;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTOConverter;
import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.user.UserService;
import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO) {
        User newUser = User.builder()
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .name(requestDTO.getName())
                .role(requestDTO.getRole())
                .build();

        if(newUser.getRole() == null) {
            newUser.setRole(UserRole.CUSTOMER);
        }

        User savedUser = userRepository.save(newUser);

        return UserSignUpResponseDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .build();
    }

    public GetAllUsersResponseDTO getAllUsers() {
        Iterable<User> results = userRepository.findAll();

        final GetAllUsersResponseDTO response = new GetAllUsersResponseDTO();
        response.setUsers(Streams.stream(results)
                .map(UserDTOConverter::convertToDTO)
                .collect(Collectors.toList()));

        return response;
    }
}
