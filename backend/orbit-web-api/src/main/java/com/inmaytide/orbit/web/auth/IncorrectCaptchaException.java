package com.inmaytide.orbit.web.auth;

import javax.naming.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("Incorrect captcha.");
    }

}
