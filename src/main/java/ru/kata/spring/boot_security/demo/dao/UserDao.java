package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findUserByLogin(String login);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
