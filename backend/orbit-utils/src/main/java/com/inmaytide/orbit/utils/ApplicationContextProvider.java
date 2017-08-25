package com.inmaytide.orbit.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Objects.requireNonNull(context);
        return context;
    }

    public static <T> T getBean(Class<T> cls) {
        return getApplicationContext().getBean(cls);
    }

    public static <T> T getBean(String name, Class<T> cls) {
        return getApplicationContext().getBean(name, cls);
    }
}
