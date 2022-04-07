package com.appointr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerAlreadyExistsException extends ResponseStatusException {
    public CustomerAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "Customer already Exists");
    }
}
