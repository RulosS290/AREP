package co.edu.microSpringBoot;

import java.lang.reflect.Method;

public class ReflectionUtil {
    public static void invokeMethod(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getMethod(methodName);
        method.invoke(obj);
    }
}
