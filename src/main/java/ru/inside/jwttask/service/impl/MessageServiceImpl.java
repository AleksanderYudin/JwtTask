package ru.inside.jwttask.service.impl;

import org.springframework.stereotype.Service;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.repository.MessageRepository;
import ru.inside.jwttask.service.MessageService;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean save(String text, User user) {
        Message message = new Message();
        message.setUser(user);
        message.setText(text);
        messageRepository.save(message);
        return true;
    }

    @Override
    public Integer matchMessage(String text) {
        Pattern pattern = Pattern.compile("history\\s+\\d+");
        if(pattern.matcher(text).matches()) {
            return Integer.valueOf(text.replaceAll("\\D", ""));
        }
        return null;
    }

    @Override
    public List<Message> getLastMessages(long user_id, int number) {
        return messageRepository.getLastMessagesByUserId(user_id, number);
    }
}
