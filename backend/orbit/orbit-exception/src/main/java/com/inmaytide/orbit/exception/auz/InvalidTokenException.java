package com.inmaytide.orbit.exception.auz;

import org.apache.shiro.authc.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
