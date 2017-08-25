package com.inmaytide.orbit.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)")
    public void beforeAdvice(JoinPoint point) {

    }

    @Before(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)")
    public void afterReturnAdvice(JoinPoint point) {
        logService.insert(findLogInformationAnnotation(point), point.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(com.inmaytide.orbit.log.LogAnnotation)", throwing = "e")
    public void afterThrowAdvice(JoinPoint point, Throwable e) {
        logService.insert(findLogInformationAnnotation(point), e, point.getSignature().getName());

    }
}
