package com.inmaytide.orbit.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author Moss
 * @since September 22, 2017
 */
public class I18nMessages {

    private static final Logger log = LoggerFactory.getLogger(I18nMessages.class);

    private static final String UN_KNOWN_MESSAGE = "unknown";

    private MessageSource messageSource;

    public I18nMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
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
        return "UN_KNOWN_MESSAGE".equals(value) ? defaultValue : value;
    }

}
