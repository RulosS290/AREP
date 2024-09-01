package co.edu.MicroSpringBoot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.net.URL;
import java.util.Map;
import java.net.MalformedURLException;
import java.lang.reflect.InvocationTargetException;


public class SpringECI {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException,InvocationTargetException {
        System.out.println("Hello World!");
        Class c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap();        
        if(c.isAnnotationPresent(RestController.class)){
            Method[] methods = c.getDeclaredMethods();
            for(Method m : methods){
                if(m.isAnnotationPresent(GetMapping.class)){
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }
    }
}