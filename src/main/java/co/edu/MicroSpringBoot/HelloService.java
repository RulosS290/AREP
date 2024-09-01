package co.edu.escuealing.reflexionlab.MicroSpringBoot;

@RestController
public class HelloService {
    
    @GetMapping("/hello")
   public static String hello(){
       return "Hello World!";
   }
}