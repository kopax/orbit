package com.inmaytide.orbit.web.auth.exception;

import org.apache.shiro.authc.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
