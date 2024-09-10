package co.edu.escuelaing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogServiceController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/log")
    public void logMessage(@RequestBody String content) {
        messageService.saveMessage(content);
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getLastMessages();
    }
}






