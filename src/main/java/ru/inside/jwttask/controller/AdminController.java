package ru.inside.jwttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/api/admin")
@RequiredArgsConstructor
@Tag(name = "admin", description = "The admin API")
public class AdminController {

    private final UserService userService;

    @Operation(summary = "Gets all users", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
                    }),
            @ApiResponse(
                    responseCode = "204",
                    description = "No users")
    })
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = new ArrayList<>();

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        users.forEach(user -> usersDto.add(UserDto.fromUser(user)));
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @Operation(summary = "Adds user", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User added",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "User not added",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    })
    })
    @PostMapping(value = "/users")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {

        if (userService.addUser(userDto) == false)
            return new ResponseEntity<>("Пользователь с таким именем уже существует",
                    HttpStatus.CONFLICT);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
