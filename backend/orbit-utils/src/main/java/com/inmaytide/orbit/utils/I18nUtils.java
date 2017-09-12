package com.inmaytide.orbit.utils;

import com.inmaytide.orbit.adepter.LogAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class I18nUtils implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(I18nUtils.class);

    @Value("#{ @environment['spring.messages.basename'] ?: 'messages' }")
    private String basename;

    private static I18nUtils instance;

    private Map<String, Map<String, String>> cache = new ConcurrentHashMap<>(4);

    private MessageSource messageSource;

    @Autowired
    public I18nUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private Locale resolveLocale(String lang) {
        Assert.hasText(lang, "The argument => lang must be not empty.");
        return Locale.forLanguageTag(lang);
    }

    private Map<String, String> generateI18nResources(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(basename, locale);
        Map<String, String> map = new HashMap<>(bundle.keySet().size());
        bundle.keySet().forEach(key -> map.put(key, bundle.getString(key)));
        if (!map.isEmpty()) {
            cache.put(locale.toLanguageTag(), map);
        }
        return map;
    }

    private Map<String, String> getI18nResources(Locale locale) {
        return cache.containsKey(locale.toLanguageTag())
                ? cache.get(locale.toLanguageTag())
                : generateI18nResources(locale);
    }

    public Map<String, String> getI18nResources() {
        Locale locale = LocaleContextHolder.getLocale();
        return getI18nResources(locale);
    }

    public Map<String, String> getI18nResources(String lang) {
        Locale locale = resolveLocale(lang);
        return getI18nResources(locale);
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
        if (instance == null) {
            instance = ApplicationContextProvider.getBean(I18nUtils.class);
        }
        return instance;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
