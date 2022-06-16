package com.appointr.controllers;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.appointr.dto.user.*;
import com.appointr.repository.entity.User;
import com.appointr.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.HeuristicCommitException;
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

    @PutMapping("/edit-me")
    public ResponseEntity<UserDTO> updateMe(
            @RequestBody @Valid EditUserDTORequest newData
            ) throws Exception {
        try {
            UserDTO response = userService.editMyProfile(newData);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/my-profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getMyProfile());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @DeleteMapping("/my-profile")
    public ResponseEntity<DeleteUserResponseDTO> deleteMyProfile() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteMyProfile());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}