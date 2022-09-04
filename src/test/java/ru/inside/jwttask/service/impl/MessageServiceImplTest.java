//package ru.inside.jwttask.service.impl;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import ru.inside.jwttask.model.Message;
//import ru.inside.jwttask.model.User;
//import ru.inside.jwttask.repository.MessageRepository;
//import ru.inside.jwttask.service.MessageService;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MessageServiceImplTest {
//
//    @Autowired
//    private MessageService messageService;
//
//    @MockBean
//    private MessageRepository messageRepository;
//
//    @Test
//    void save() {
//        User user = new User();
//        String text = "any text";
//        boolean isMessageCreated = messageService.save(text, user);
//
//        assertTrue(isMessageCreated);
//
//        Message message = new Message();
//        message.setUser(user);
//        message.setText(text);
//
//        Mockito.verify(messageRepository, Mockito.times(1)).save(message);
//    }
//
//    @Test
//    void matchMessage() {
//        String text1 = "history 5";
//        String text2 = "History 5";
//        String text3 = "history   3";
//        String text4 = "history 789";
//        String text5 = "history78";
//
//        assertEquals(messageService.matchMessage(text1), 5);
//        assertNull(messageService.matchMessage(text2));
//        assertEquals(messageService.matchMessage(text3), 3);
//        assertEquals(messageService.matchMessage(text4), 789);
//        assertNull(messageService.matchMessage(text5));
//    }
//}