package co.edu.microspringboot;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from MicroSpring Boot!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam("name") String name) {
        return "Hello, " + name + "!";
    }
}
