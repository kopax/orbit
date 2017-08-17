package com.inmaytide.orbit.web.auth.exception;


import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("Incorrect captcha.");
    }

}
