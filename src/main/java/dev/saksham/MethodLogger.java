package dev.saksham;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface MethodLogger {
    boolean enter() default true;
    boolean exit() default true;
    boolean parameters() default true;
    boolean returnValue() default true;
}
