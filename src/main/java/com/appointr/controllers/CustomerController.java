package com.appointr.controllers;


import com.appointr.repository.CustomerReposity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerReposity customerReposity;

    public CustomerController(CustomerReposity customerReposity) {
        this.customerReposity = customerReposity;
    }


}