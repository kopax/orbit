package com.inmaytide.orbit.web.auth;


import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("Incorrect captcha.");
    }

}
