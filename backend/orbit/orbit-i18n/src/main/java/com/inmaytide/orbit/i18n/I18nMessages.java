package com.inmaytide.orbit.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Moss
 * @since September 22, 2017
 */
public class I18nMessages implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(I18nMessages.class);

    private static final String UN_KNOWN_MESSAGE = "unknown";

    private static ApplicationContext applicationContext;

    private static I18nMessages instance;

    private MessageSource messageSource;

    public I18nMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static I18nMessages getInstance() {
        Objects.requireNonNull(applicationContext);
        if (instance == null) {
            instance = applicationContext.getBean(I18nMessages.class);
        }
        return instance;
    }

    public String get(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            log.info("No such message for code => {}", key);
            return UN_KNOWN_MESSAGE;
        }
    }

    public String get(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return get(key, locale, args);
    }

    public String get(String key, String defaultValue) {
        String value = get(key);
        return UN_KNOWN_MESSAGE.equals(value) ? defaultValue : value;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        I18nMessages.applicationContext = applicationContext;
    }
}
