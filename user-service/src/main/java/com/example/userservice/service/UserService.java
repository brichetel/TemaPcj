package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User updateUser(User user);

    boolean deleteUser(String id);
}
