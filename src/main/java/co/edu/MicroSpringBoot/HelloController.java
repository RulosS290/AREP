package co.edu.microSpringBoot;

import javax.print.DocFlavor.STRING;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
