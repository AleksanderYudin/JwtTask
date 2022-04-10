package ru.inside.jwttask.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.inside.jwttask.model.Message;
import ru.inside.jwttask.model.User;
import ru.inside.jwttask.repository.MessageRepository;
import ru.inside.jwttask.service.MessageService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    void save() {
        User user = new User();
        String text = "any text";
        Integer result = messageService.save(text, user);

        assertEquals(result, -1);

        Message message = new Message();
        message.setUser(user);
        message.setText(text);

        Mockito.verify(messageRepository, Mockito.times(1)).save(message);
    }
}