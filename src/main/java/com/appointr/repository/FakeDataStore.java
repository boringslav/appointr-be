package com.appointr.repository;

import com.appointr.model.User;
import com.appointr.model.UserImpl;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class FakeDataStore {
    private final List<User> userList = new ArrayList<>();

    public FakeDataStore() {
        User bobcho = new UserImpl("1", "nznznz@gmai.com","Borislav", "Stoyanov", "123123");
        User bobcho1 = new UserImpl("11", "nznznz@gmai.com","Borislav", "Stoyanov", "123123");

        userList.add(bobcho);
        userList.add(bobcho1);
    }

    public List<User> getUsers() {
        return this.userList;
    }
    public User getUser(String userId) {
        for (User user : userList) {
            if (user.getID().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
