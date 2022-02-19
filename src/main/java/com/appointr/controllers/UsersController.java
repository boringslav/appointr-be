package com.appointr.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/{id}")
    @ResponseBody
    public String test (@PathVariable(value= "id") String id) {
        return "Test /users/" + id;
    }
}
