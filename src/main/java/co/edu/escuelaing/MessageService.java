package co.edu.escuelaing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setDate(new Date());
        messageRepository.save(message);
    }

    public List<Message> getLastMessages() {
        return messageRepository.findTop10ByOrderByDateDesc();
    }
}






