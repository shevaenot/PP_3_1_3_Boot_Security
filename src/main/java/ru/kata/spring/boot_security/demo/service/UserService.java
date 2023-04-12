package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findUserByLogin(String login);

    List<User> getAllUsers();

    String saveUser(User user);

    void updateUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
