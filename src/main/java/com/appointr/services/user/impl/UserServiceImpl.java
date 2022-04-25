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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO) {
        User newUser = User.builder()
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .name(requestDTO.getName())
                .role(requestDTO.getRole())
                .build();

        if (newUser.getRole() == null) {
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
