package com.github.firelcw.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Var {
    String TYPE = "var";
    String value() default "";
}
