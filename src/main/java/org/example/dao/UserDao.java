package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {

    void createUserTable();

    void dropUserTable();

    void clearTable();

    void createUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    void updateUser(Long id,User newUser);

    void deleteUser(Long id);

    List<User> searchUsersByName(String username);
}