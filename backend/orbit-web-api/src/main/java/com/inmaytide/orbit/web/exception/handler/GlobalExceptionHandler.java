package com.inmaytide.orbit.web.exception.handler;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.exceptions.InvalidParameterException;
import com.inmaytide.orbit.exceptions.ResponseErrorCodes;
import com.inmaytide.orbit.exceptions.VersionMatchedException;
import com.inmaytide.orbit.http.ErrorResult;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.utils.I18nUtils;
import com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException;
import com.inmaytide.orbit.web.auth.exception.InvalidTokenException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        ResponseEntity<Object> entity = this.handleException(e, request);
        return this.handleExceptionInternal(e, null, entity.getHeaders(), entity.getStatusCode(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse restResponse;
        if (e instanceof AuthenticationException) {
            restResponse = loginExceptionHandler(e);
        } else if (e instanceof UnauthorizedException) {
            restResponse = RestResponse.of(Integer.toString(HttpStatus.FORBIDDEN.value()), "您暂时没有权限");
        } else if (e instanceof VersionMatchedException) {
            restResponse = RestResponse.of(ResponseErrorCodes.VersionMatched.getCode(), "数据已经被修改或不存在", ((VersionMatchedException) e).getData());
        } else if (e instanceof InvalidParameterException) {
            restResponse = RestResponse.of(ResponseErrorCodes.InvalidParameter.getCode(), e.getMessage(), ((InvalidParameterException) e).getErrors());
        } else {
            restResponse = RestResponse.of(status, ErrorResult.of(e));
        }
        error(e.getClass().getName(), e);
        return super.handleExceptionInternal(e, restResponse, headers, status, request);
    }

    private RestResponse loginExceptionHandler(Throwable e) {
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
            key = "login.error.invalid.token";
        } else {
            key = "login.error";
        }
        return RestResponse.of(Integer.toString(HttpStatus.UNAUTHORIZED.value()), I18nUtils.getInstance().getValue(key));
    }


    @Override
    public Logger getLogger() {
        return log;
    }
}
