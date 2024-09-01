package co.edu.microSpringBoot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler {
    private final Map<String, Method> handlerMappings = new HashMap<>();

    public RequestMappingHandler(Class<?>... controllerClasses) {
        for (Class<?> controllerClass : controllerClasses) {
            for (Method method : controllerClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                    String uri = mapping.value();
                    handlerMappings.put(uri, method);
                }
            }
        }
    }

    public Method getHandler(String uri) {
        return handlerMappings.get(uri);
    }
}
