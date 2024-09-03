package co.edu.microspringboot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.net.URL;
import java.util.Map;
import java.net.MalformedURLException;
import java.lang.reflect.InvocationTargetException;

public class MicroSpringBoot {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException,InvocationTargetException {
        System.out.println("Hello World!");
        Class<?> c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap<>();        
        if(c.isAnnotationPresent(RestController.class)){
            Method[] methods = c.getDeclaredMethods();
            for(Method m : methods){
                if(m.isAnnotationPresent(GetMapping.class) || m.isAnnotationPresent(RequestMapping.class)){
                    String key;
                    if(m.isAnnotationPresent(GetMapping.class)){
                        key = m.getAnnotation(GetMapping.class).value();
                    } else {
                        key = m.getAnnotation(RequestMapping.class).value();
                    }
                    services.put(key, m);
                }
            }
        }
        
        // Codigo quemado para ejemplo
        URL serviceurl = new URL("http://localhost:8080/App/test/hello");
        String path = serviceurl.getPath();
        System.out.println("Path: " + path);
        String servicename = path.equals("/") ? path : path.substring(4);
        System.out.println("Service name: " + servicename);

        serviceurl = new URL("http://localhost:8080/App/test/PI");
        path = serviceurl.getPath();
        System.out.println("Path: " + path);
        servicename = path.substring(4);
        System.out.println("Service name: " + servicename);

        serviceurl = new URL("http://localhost:8080/App/monda");
        path = serviceurl.getPath();
        System.out.println("Path: " + path);
        servicename = path.substring(4);
        System.out.println("Service name: " + servicename);
    }
}


