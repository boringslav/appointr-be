package com.appointr.controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("sign-up")
public class SignUpController {
    @GetMapping
    @ResponseBody
    public String test(){
        return "Test Request on /sign-up";
    }
}
