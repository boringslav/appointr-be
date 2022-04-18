package com.appointr.services.user;

import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService {

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
}
