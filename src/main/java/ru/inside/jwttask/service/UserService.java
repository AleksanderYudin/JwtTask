package ru.inside.jwttask.service;


import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.User;

import java.util.List;

public interface UserService {

    boolean addUser(UserDto user);

    User findByUsername(String username);

    List<User> getUsers();
}
