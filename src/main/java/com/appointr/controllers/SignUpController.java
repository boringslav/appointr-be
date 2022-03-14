package com.appointr.controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin //TODO: look for a global cors configuration
@RestController
@RequestMapping("sign-up")
public class SignUpController {
    @GetMapping
    @ResponseBody
    public String test(){
        return "Test Request on /sign-up";
    }
}
