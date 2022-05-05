package com.example.userservice.service;

import com.example.userservice.datasource.UserDataSource;
import com.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    private final UserDataSource userDb;

    @Autowired
    public UserServiceImplementation(UserDataSource userDb) {
        this.userDb = userDb;
    }

    @Override
    public List<User> getAllUsers() {
        return userDb.getAllUsers();
    }

    @Override
    public User saveUser(User user) {
        return userDb.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDb.updateUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userDb.deleteUser(id);
    }
}
