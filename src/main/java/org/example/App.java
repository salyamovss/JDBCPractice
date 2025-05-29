package org.example;

import org.example.dao.UserDao;
import org.example.dao.PostDao;
import org.example.dao.daoImpl.UserDaoImpl;
import org.example.dao.daoImpl.PostDaoImpl;
import org.example.model.User;
import org.example.model.Post;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoImpl();
        PostDao postDao = new PostDaoImpl();


    }
}
