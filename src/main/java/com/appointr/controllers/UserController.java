package com.appointr.controllers;


import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;
import com.appointr.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<GetAllUsersResponseDTO> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") final long id) {
        try {
            final UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResponseDTO> signUp(
            @RequestBody @Valid UserSignUpRequestDTO signUpRequestObject
    ) {
        UserSignUpResponseDTO response = userService.signUp(signUpRequestObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}