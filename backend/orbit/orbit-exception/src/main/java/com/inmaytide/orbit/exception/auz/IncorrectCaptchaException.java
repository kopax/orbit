package com.inmaytide.orbit.exception.auz;


import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("Incorrect captcha.");
    }

}
