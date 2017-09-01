package com.inmaytide.orbit.web.handler;

import com.inmaytide.orbit.model.basic.Result;
import com.inmaytide.orbit.utils.I18nUtils;
import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException;
import com.inmaytide.orbit.web.auth.exception.InvalidTokenException;
import com.inmaytide.orbit.web.controller.BasicController;
import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Throwable.class)
    public Result defaultErrorHandler(HttpServletRequest request, Throwable e) {
        if (e instanceof AuthenticationException) {
            return loginErrorHandler(request, e);
        }
        info("handler default exception => ", e);
        return Result.ofFail(e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public Result loginErrorHandler(HttpServletRequest request, Throwable e) {
        info("handler login exception => ", e);
        return loginExceptionHandler(e);
    }

    private Result loginExceptionHandler(Throwable e) {
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
        } else if (e instanceof InvalidTokenException) {
            key = "login.invalid.token";
        } else {
            key = "login.error";
        }
        return Result.ofFail(I18nUtils.getInstance().getValue(key));
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
