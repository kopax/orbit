package com.inmaytide.orbit.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface LogAnnotation {

    String description();

    String success() default "操作成功";

    /**
     * 默认读取Exception的Message
     */
    String failure() default "";
}
