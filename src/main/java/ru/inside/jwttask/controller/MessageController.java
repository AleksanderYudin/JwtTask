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
import ru.inside.jwttask.dto.MessageDto;
import ru.inside.jwttask.dto.UserDto;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.security.jwt.JwtTokenProvider;
import ru.inside.jwttask.service.MessageService;
import ru.inside.jwttask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("v1/api/messages")
@RequiredArgsConstructor
@Tag(name = "message", description = "The message API")
public class MessageController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final MessageService messageService;

    @Operation(summary = "Add message / Get message history", tags = "message")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Message added"),
            @ApiResponse(
                    responseCode = "200",
                    description = "Message history",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MessageDto.class)))
                    })
    })
    @PostMapping
    public ResponseEntity<List<MessageDto>> addMessage(HttpServletRequest req,
                                                     @RequestBody MessageDto messageDto){
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
        User user = userService.findByUsername(username);

        Integer match = messageService.matchMessage(messageDto.getMessage());
        if (match == null) {
            messageService.save(messageDto.getMessage(), user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        List<Message> messages = messageService.getLastMessages(user.getId(), match);
        List<MessageDto> messagesDto = MessageDto.fromMessages(messages);
        return new ResponseEntity<>(messagesDto, HttpStatus.OK);
    }

}
