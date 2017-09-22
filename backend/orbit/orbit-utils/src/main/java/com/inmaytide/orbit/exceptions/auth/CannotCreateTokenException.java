package com.inmaytide.orbit.exceptions.auth;

import org.apache.shiro.authc.AuthenticationException;

public class CannotCreateTokenException extends AuthenticationException {

    public CannotCreateTokenException() {
        super("Cannot create token.");
    }

}
