package com.appointr.controllers;

import org.springframework.web.bind.annotation.*;
@CrossOrigin //TODO: look for a global cors configuration
@RestController
@RequestMapping("/sign-in")

public class SignInController {
    @GetMapping
    @ResponseBody
    public String test() {
        return "Test Request on /sign-in";
    }
}
