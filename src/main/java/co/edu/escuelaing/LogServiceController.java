package co.edu.escuelaing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

@RestController
public class LogServiceController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private LoadBalancerService loadBalancerService;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/log")
    public void logMessage(@RequestBody String content) {
        String logServiceUrl = loadBalancerService.getNextLogServiceUrl();
        try {
            restTemplate.postForObject(logServiceUrl, content, Void.class);
            System.out.println("Message sent to: " + logServiceUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send message to: " + logServiceUrl);
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getLastMessages();
    }
}
