package co.edu.microSpringBoot;

import java.lang.reflect.Constructor;

public class IoCFramework {
    private Object bean;

    public IoCFramework(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getConstructor();
        this.bean = constructor.newInstance();
    }

    public Object getBean() {
        return bean;
    }
}
