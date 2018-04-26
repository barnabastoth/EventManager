package application.utils;

import application.model.authentication.Message;
import application.model.authentication.NewMessage;
import application.repository.MessageRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageUtils {

    @Autowired MessageRepository messageRepository;
    @Autowired UserRepository userRepository;

    public void saveNewMessage(NewMessage newMessage) {
        Message message = new Message();
        message.setMessage(newMessage.getMessage());
        message.setReceiver(userRepository.findByUsername(newMessage.getReceiver()));
        message.setSender(userRepository.findByUsername(newMessage.getSender()));
        message.setDate(LocalDateTime.now());
        messageRepository.saveAndFlush(message);
    }
}
