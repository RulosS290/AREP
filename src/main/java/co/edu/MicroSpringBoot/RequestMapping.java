package co.edu.microSpringBoot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();
    HttpMethod method() default HttpMethod.GET;
    String[] params() default {};
    String[] headers() default {};
}

