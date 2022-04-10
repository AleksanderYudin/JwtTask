package ru.inside.jwttask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.inside.jwttask.model.Message;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private Long id;
    private String message;
    private String name;

    public static MessageDto fromMessage(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage(message.getText());
        messageDto.setName(message.getUser().getUsername());
        return messageDto;
    }

    public static List<MessageDto> fromMessages(List<Message> messages){
        List<MessageDto> messageDtoList = new ArrayList<>();
        messages.forEach(message -> messageDtoList.add(fromMessage(message)));
        return messageDtoList;
    }
}
