package com.inmaytide.orbit.service.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class I18nServiceImpl implements I18nService {

    private static final Logger log = LoggerFactory.getLogger(I18nServiceImpl.class);

    private static final String UN_KNOWN_MESSAGE = "unknown";

    private Map<Locale, Map<String, String>> cache = new ConcurrentHashMap<>(4);

    @Resource
    private MessageSource messageSource;

    @Value("#{ @environment['orbit.frontend.message-cache'] ?: 'messages' }")
    private boolean isCache = true;

    @Value("#{ @environment['spring.messages.basename'] ?: 'messages' }")
    private String basename;

    @Override
    public Map<String, String> getI18nResources() {
        Locale locale = LocaleContextHolder.getLocale();
        return getI18nResources(locale);
    }

    @Override
    public Map<String, String> getI18nResources(String lang) {
        Locale locale = resolveLocale(lang);
        return getI18nResources(locale);
    }

    @Override
    public String getValue(String key, Locale locale, Object... args) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            log.info("No such message for code => {}", key);
            return UN_KNOWN_MESSAGE;
        }
    }

    @Override
    public String getValue(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return getValue(key, locale, args);
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        return "UN_KNOWN_MESSAGE".equals(value) ? defaultValue : value;
    }

    private Locale resolveLocale(String lang) {
        Assert.hasText(lang, "The argument => lang must be not empty.");
        return Locale.forLanguageTag(lang);
    }

    private Map<String, String> generateI18nResources(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(basename, locale);
        Map<String, String> map = new HashMap<>(bundle.keySet().size());
        bundle.keySet().forEach(key -> map.put(key, bundle.getString(key)));
        if (!map.isEmpty() && isCache) {
            cache.put(locale, map);
        }
        return map;
    }

    private Map<String, String> getI18nResources(Locale locale) {
        return cache.containsKey(locale)
                ? cache.get(locale)
                : generateI18nResources(locale);
    }

}
