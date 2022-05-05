package com.example.userservice.datasource;

import com.example.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserDataSource {
    private List<User> users;

    public UserDataSource(){
        users = Stream.of(
                new User("1", "dada", "dada", "dada.dada@gmail.com", "dada dada", "mechanic"),
                new User("2","Vlad", "Codina", "Vlad.Codina@gmail.com", "Strada Inimii", "car owner"),
                new User("3","Ionut", "Dulla", "Ionut.Dulla@gmail.com", "Strada Kosuth Lajos", "car owner"),
                new User("4","Emil", "Constantinescu", "Emil.Constantinescu@gmail.com", "Strada Harghitei", "mechanic")
        ).collect(Collectors.toList());
    }

    public List<User> getAllUsers(){
        return users;
    }

    public Optional<User> findUserById(String id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public Optional<User> findUserByEmail(String email){
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public User saveUser(User user){
        findUserById(user.getId()).ifPresent(i -> {
            throw new RuntimeException("User with id " + i.getId() + " already exists!");
        });
        findUserByEmail(user.getEmail()).ifPresent(i -> {
            throw new RuntimeException("User with email " + i.getEmail() + " already exists!");
        });
        this.users.add(user);

        return user;
    }

    public User updateUser(User user){
        User existingUser = findUserById(user.getId()).orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found!"));

        int index = users.indexOf(existingUser);

        users.get(index).setAddress(user.getAddress());
        users.get(index).setEmail(user.getEmail());
        users.get(index).setFirstName(user.getFirstName());
        users.get(index).setType(user.getType());
        users.get(index).setLastName(user.getLastName());

        return user;
    }

    public boolean deleteUser(String id){
        User userToDelete = findUserById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found!"));

        return users.remove(userToDelete);
    }
}
