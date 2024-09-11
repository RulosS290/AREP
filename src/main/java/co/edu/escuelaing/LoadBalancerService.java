package co.edu.escuelaing;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoadBalancerService {

    private final List<String> logServiceUrls = List.of(
        "http://localhost:35001/log",
        "http://localhost:35002/log",
        "http://localhost:35003/log"
    );

    private int currentIndex = 0;

    public String getNextLogServiceUrl() {
        String url = logServiceUrls.get(currentIndex);
        currentIndex = (currentIndex + 1) % logServiceUrls.size();
        System.out.println("Current Log Service URL: " + url); // Registro para depuración
        return url;
    }
}
