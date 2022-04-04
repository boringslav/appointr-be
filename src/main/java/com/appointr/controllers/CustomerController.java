package com.appointr.controllers;


import com.appointr.dto.customer.CustomerDTO;
import com.appointr.dto.customer.GetAllCustomersResponseDTO;
import com.appointr.services.customer.GetCustomerById;
import com.appointr.services.customer.GetCustomers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final GetCustomers getCustomers;
    private final GetCustomerById getCustomerById;

    public CustomerController(GetCustomers getCustomers,GetCustomerById getCustomerById) {
        this.getCustomers = getCustomers;
        this.getCustomerById =getCustomerById;
    }

    @GetMapping
    public ResponseEntity<GetAllCustomersResponseDTO> getAllCustomers() {
        return ResponseEntity.ok(getCustomers.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value="id") final long id) {
        final Optional<CustomerDTO> customer = getCustomerById.getCustomer(id);
        if(customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer.get());
    }


}