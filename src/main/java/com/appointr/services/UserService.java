package com.appointr.services;

import com.appointr.model.User;
import com.appointr.repository.FakeDataStore;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final FakeDataStore fakeDataStore;

    public List<User> getUsers() {
        return fakeDataStore.getUsers();
    }

    public User getUser(String userId) {
        return fakeDataStore.getUser(userId);
    }
}
