package com.appointr.controllers;

import org.springframework.web.bind.annotation.*;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/sign-in")

public class SignInController {
    @GetMapping
    @ResponseBody
    public String test() {
        return "Test Request on /sign-in";
    }
}
