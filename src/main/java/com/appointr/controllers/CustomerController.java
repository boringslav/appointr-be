package com.appointr.controllers;


import com.appointr.dto.customer.GetAllCustomersResponseDTO;
import com.appointr.repository.CustomerReposity;
import com.appointr.services.customer.GetCustomers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final GetCustomers getCustomers;

    public CustomerController(GetCustomers getCustomers) {
        this.getCustomers = getCustomers;
    }

    @GetMapping
    public ResponseEntity<GetAllCustomersResponseDTO> getAllCustomers() {
        return ResponseEntity.ok(getCustomers.getAllCustomers());
    }
}