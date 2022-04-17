package com.appointr.controllers;


import com.appointr.dto.user.UserSignUpRequestDTO;
import com.appointr.dto.user.UserSignUpResponseDTO;
import com.appointr.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResponseDTO> signUp(
            @RequestBody @Valid UserSignUpRequestDTO signUpRequestObject
    ) {
        UserSignUpResponseDTO response = userService.signUp(signUpRequestObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}