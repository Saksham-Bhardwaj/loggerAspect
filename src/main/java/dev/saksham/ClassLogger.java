package dev.saksham;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.TYPE})
public @interface ClassLogger{
    boolean enter() default true;
    boolean exit() default true;
    boolean parameters() default true;
    boolean returnValue() default true;
}
