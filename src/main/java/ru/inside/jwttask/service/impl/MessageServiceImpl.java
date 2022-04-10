package ru.inside.jwttask.service.impl;

import org.springframework.stereotype.Service;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.repository.MessageRepository;
import ru.inside.jwttask.service.MessageService;
import ru.inside.jwttask.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Integer save(String text, User user) {

        Pattern pattern = Pattern.compile("history\\s+\\d+");
        if(pattern.matcher(text).matches()) {
            return Integer.valueOf(text.replaceAll("\\D",""));
        }

        Message message = new Message();
        message.setUser(user);
        message.setText(text);

        messageRepository.save(message);
        return -1;
    }

    @Override
    public List<Message> getLastMessages(long user_id, int number) {
        return messageRepository.getLastMessagesByUserId(user_id, number);
    }

}
