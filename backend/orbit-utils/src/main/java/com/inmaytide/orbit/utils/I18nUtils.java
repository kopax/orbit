package com.inmaytide.orbit.utils;

import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18nUtils implements ApplicationContextAware, LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(I18nUtils.class);

    private static I18nUtils instance;

    private static ApplicationContext applicationContext;

    private MessageSource messageSource;

    @Autowired
    public I18nUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getValue(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            info("No such message for code => {}", key);
            return "unknown";
        }
    }

    public String getValue(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return getValue(key, locale, args);
    }

    public static I18nUtils getInstance() {
        Assert.notNull(applicationContext);
        if (instance == null) {
            instance = applicationContext.getBean(I18nUtils.class);
        }
        return instance;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        I18nUtils.applicationContext = applicationContext;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
