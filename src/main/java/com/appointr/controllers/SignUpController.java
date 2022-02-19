package com.appointr.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sign-up")
public class SignUpController {
    @GetMapping
    @ResponseBody
    public String test(){
        return "Test Request on /sign-up";
    }
}
