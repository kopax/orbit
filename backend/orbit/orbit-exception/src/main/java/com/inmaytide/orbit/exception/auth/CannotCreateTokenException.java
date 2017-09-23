package com.inmaytide.orbit.exception.auth;

import org.apache.shiro.authc.AuthenticationException;

public class CannotCreateTokenException extends AuthenticationException {

    public CannotCreateTokenException() {
        super("Cannot create token.");
    }

}
