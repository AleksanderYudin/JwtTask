package ru.inside.jwttask.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inside.jwttask.dto.MessageDto;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.security.jwt.JwtTokenProvider;
import ru.inside.jwttask.service.MessageService;
import ru.inside.jwttask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final MessageService messageService;

    public MessageController(JwtTokenProvider jwtTokenProvider, UserService userService, MessageService messageService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<List<MessageDto>> addMessage(HttpServletRequest req,
                                                     @RequestBody MessageDto messageDto){
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
        User user = userService.findByUsername(username);

        int condition = messageService.save(messageDto.getMessage(), user);
        if (condition < 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        List<Message> messages = messageService.getLastMessages(user.getId(), condition);
        List<MessageDto> messagesDto = MessageDto.fromMessages(messages);
        return new ResponseEntity<>(messagesDto, HttpStatus.OK);
    }

}
