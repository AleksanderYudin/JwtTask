package ru.inside.jwttask.service;

import ru.inside.jwttask.dto.MessageDto;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;

import java.util.List;

public interface MessageService {

    Integer save(String text, User user);

    List<Message> getLastMessages(long user_id, int number);
}
