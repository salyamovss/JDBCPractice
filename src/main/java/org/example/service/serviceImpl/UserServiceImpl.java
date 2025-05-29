package org.example.service.serviceImpl;

import org.example.dao.UserDao;
import org.example.dao.daoImpl.UserDaoImpl;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void createUserTable() {
        userDao.createUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();
    }

    @Override
    public void clearTable() {
        userDao.clearTable();
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(Long id, User newUser) {
        userDao.updateUser(id, newUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> searchUsersByName(String username) {
        return userDao.searchUsersByName(username);
    }
}
