package ru.inside.jwttask.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.repository.MessageRepository;
import ru.inside.jwttask.repository.UserRepository;
import ru.inside.jwttask.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MessageRepository messageRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null)
            return false;
        User newUser = userDto.toUser();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
