package com.appointr.controllers;

import com.appointr.model.User;
import com.appointr.repository.FakeDataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {
    //this has to be static because web services are stateless
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

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
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * TODO return all users
     * TODO return an user
     * TODO OOP with Java Book
     */
}
