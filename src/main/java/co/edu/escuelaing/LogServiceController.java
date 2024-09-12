package co.edu.escuelaing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LogServiceController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/log")
    public ResponseEntity<String> logMessage(@RequestBody String content) {
        try {
            messageService.saveMessage(content);  // Guarda el mensaje en la base de datos directamente
            return ResponseEntity.ok("Message saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save message");
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getLastMessages();
    }
}

