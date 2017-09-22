package com.inmaytide.orbit.exceptions.handler;

import com.inmaytide.orbit.exceptions.IllegalParameterException;
import com.inmaytide.orbit.exceptions.VersionMatchedException;
import com.inmaytide.orbit.exceptions.auth.IncorrectCaptchaException;
import com.inmaytide.orbit.exceptions.auth.InvalidTokenException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public enum ResponseErrorBuilders {

    Authentication(AuthenticationException.class, (e, status) -> ResponseError.of(HttpStatus.FORBIDDEN, getAuthenticationErrorMessage(e.getClass()))),
    VersionMatched(VersionMatchedException.class, (e, status) -> ResponseError.of(HttpStatus.CONFLICT, "数据已经被修改或不存在")),
    IllegalParameter(IllegalParameterException.class, (e, status) -> ResponseError.of(HttpStatus.BAD_REQUEST, e.getMessage())),
    Unauthorized(UnauthorizedException.class, (e, status) -> ResponseError.of(HttpStatus.FORBIDDEN, "没有权限")),
    Default(Exception.class, (e, status) -> ResponseError.of(status, ExceptionUtils.getStackTrace(e)));

    static Map<Class<? extends Throwable>, String> EXCEPTIONS_MESSAGES_MAPPER = new ConcurrentHashMap<>();

    static {
        EXCEPTIONS_MESSAGES_MAPPER.put(LockedAccountException.class, "login.error.user.locked");
        EXCEPTIONS_MESSAGES_MAPPER.put(UnknownAccountException.class, "login.error.username.wrong");
        EXCEPTIONS_MESSAGES_MAPPER.put(IncorrectCaptchaException.class, "login.error.captcha.wrong");
        EXCEPTIONS_MESSAGES_MAPPER.put(AccountException.class, "login.error.username.empty");
        EXCEPTIONS_MESSAGES_MAPPER.put(IncorrectCredentialsException.class, "login.error.password.wrong");
        EXCEPTIONS_MESSAGES_MAPPER.put(InvalidTokenException.class, "login.error.invalid.token");
    }

    private Class<? extends Throwable> exceptionClass;
    private ResponseErrorBuilder builder;

    ResponseErrorBuilders(Class<? extends Throwable> exceptionClass, ResponseErrorBuilder builder) {
        this.exceptionClass = exceptionClass;
        this.builder = builder;
    }

    public static ResponseErrorBuilders valueOf(Throwable e) {
        return Stream.of(values())
                .filter(method -> method.getExceptionClass().isInstance(e))
                .findFirst().orElse(Default);
    }

    public static ResponseErrorBuilder getBuilder(Throwable e) {
        return valueOf(e).getBuilder();
    }

    private static String getAuthenticationErrorMessage(Class<? extends Throwable> cls) {
        return EXCEPTIONS_MESSAGES_MAPPER.getOrDefault(cls, "login.error");
    }

    public Class<? extends Throwable> getExceptionClass() {
        return exceptionClass;
    }

    public ResponseErrorBuilder getBuilder() {
        return builder;
    }
}
