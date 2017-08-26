package com.inmaytide.orbit.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class LogAspect {

    @Resource
    private LogServiceAdapter logService;

    @Before(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)")
    public void beforeAdvice(JoinPoint point) {

    }

    @AfterReturning(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)")
    public void afterReturnAdvice(JoinPoint point) {
        logService.record(point);
    }

    @AfterThrowing(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)", throwing = "e")
    public void afterThrowAdvice(JoinPoint point, Throwable e) {
        logService.record(point, e);

    }
}
