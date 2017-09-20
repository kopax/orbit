package com.inmaytide.orbit.web.auth.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CannotCreateTokenException extends AuthenticationException {

    public CannotCreateTokenException() {
        super("Cannot create token.");
    }

}
