package com.inmaytide.orbit.exception;

public class IllegalParameterException extends RuntimeException {

    private static final long serialVersionUID = 5715228328791133259L;

    public IllegalParameterException() {
        super();
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(Throwable e) {
        super(e);
    }
}
