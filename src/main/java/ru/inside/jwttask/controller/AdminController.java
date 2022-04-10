package ru.inside.jwttask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = new ArrayList<>();

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        users.forEach(user -> usersDto.add(UserDto.fromUser(user)));
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {

        if (userService.addUser(userDto) == false)
            return new ResponseEntity<>("Пользователь с таким именем уже существует",
                    HttpStatus.CONFLICT);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
