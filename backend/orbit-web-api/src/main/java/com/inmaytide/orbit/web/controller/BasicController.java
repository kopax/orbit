package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.model.basic.Result;
import com.inmaytide.orbit.web.auth.IncorrectCaptchaException;
import org.apache.shiro.authc.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Locale;

public abstract class BasicController {

    @Resource
    private MessageSource messageSource;

    protected String i18nValue(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    @ExceptionHandler
    @ResponseBody
    public Object exception(Exception e) {

        if (e instanceof AuthenticationException) {
            return loginExceptionHandler(e);
        }

        return Result.ofFail(null, e.getMessage());
    }

    private Result loginExceptionHandler(Exception e) {
        String key = "";
        if (e instanceof LockedAccountException) {
            key = "login.error.user.locked";
        } else if (e instanceof UnknownAccountException) {
            key = "login.error.username.wrong";
        } else if (e instanceof IncorrectCaptchaException) {
            key = "login.error.captcha.wrong";
        } else if (e instanceof AccountException) {
            key = "login.error.username.empty";
        } else if (e instanceof IncorrectCredentialsException) {
            key = "login.error.password.wrong";
        } else {
            key = "login.error";
        }
        return Result.ofFail(null, i18nValue(key));
    }



}
