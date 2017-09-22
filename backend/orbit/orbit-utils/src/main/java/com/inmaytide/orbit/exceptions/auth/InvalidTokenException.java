package com.inmaytide.orbit.exceptions.auth;

import org.apache.shiro.authc.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
