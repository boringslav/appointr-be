package com.appointr.controllers;

import com.appointr.model.User;
import com.appointr.repository.FakeDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {
    private FakeDataStore fakeDataStore;
    @Autowired
    public UsersController(FakeDataStore fakeDataStore) {
        this.fakeDataStore = fakeDataStore;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = fakeDataStore.getUsers();
        if (users.size() > 0) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") String id) {
        User user = fakeDataStore.getUser(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
