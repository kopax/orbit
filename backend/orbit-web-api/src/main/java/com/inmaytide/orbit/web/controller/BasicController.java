package com.inmaytide.orbit.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Locale;

public abstract class BasicController {

    @Resource
    private MessageSource messageSource;

    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    protected String i18nValue(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }


}
