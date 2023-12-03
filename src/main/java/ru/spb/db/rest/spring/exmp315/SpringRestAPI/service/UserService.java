package ru.spb.db.rest.spring.exmp315.SpringRestAPI.service;

import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserById (Long id);

    List<User> findAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);


}
