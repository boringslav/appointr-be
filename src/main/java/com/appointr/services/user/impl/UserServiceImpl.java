package com.appointr.services.user.impl;

import com.appointr.dto.user.*;
import com.appointr.repository.UserRepository;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import com.appointr.services.user.UserService;
import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
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

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        Optional<User> foundUser = userRepository.findUserById(id);
        if (foundUser.isEmpty()) {
            throw new Exception("User Not Found");
        }
        User user = foundUser.get();
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getEmail())
                .build();
    }

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        if(foundUser.isEmpty()) {
            throw new Exception("User Not Found");
        }
        User user = foundUser.get();
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getEmail())
                .build();
    }

    @Override
    public UserDTO editMyProfile(EditUserDTORequest newData) throws Exception {
        String loggedInUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByEmail(loggedInUserEmail).get();

        if(!newData.getEmail().equals(null)) {
            foundUser.setEmail(newData.getEmail());
        }
        if(!newData.getName().equals(null)) {
            foundUser.setName(newData.getName());
        }
        if(!newData.getPassword().equals(null)) {
            foundUser.setPassword(passwordEncoder.encode(newData.getPassword()));
        }

        User savedUser = userRepository.save(foundUser);

        return UserDTOConverter.convertToDTO(savedUser);
    }

    @Override
    public UserDTO getMyProfile() throws Exception {
        String loggedInUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByEmail(loggedInUserEmail).get();
        return UserDTOConverter.convertToDTO(foundUser);
    }

}
