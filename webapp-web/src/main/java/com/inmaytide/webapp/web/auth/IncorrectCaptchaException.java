package com.inmaytide.webapp.web.auth;

import javax.naming.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("Incorrect captcha.");
    }

}
