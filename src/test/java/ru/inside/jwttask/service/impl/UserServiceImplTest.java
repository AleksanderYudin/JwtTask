package ru.inside.jwttask.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.repository.UserRepository;
import ru.inside.jwttask.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void addUser() {
        UserDto userDto = new UserDto();
        boolean isUserCreated = userService.addUser(userDto);
        assertTrue(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(1)).save(userDto.toUser());
    }

    @Test
    void addUserFailTest() {
        UserDto userDto = new UserDto();
        userDto.setUsername("Alex");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername(userDto.getUsername());
        boolean isUserCreated = userService.addUser(userDto);

        assertFalse(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(0)).save(userDto.toUser());
    }

}