package com.inmaytide.orbit.exceptions;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class InvalidParameterException extends RuntimeException {

    private static final long serialVersionUID = 5715228328791133259L;

    private final List<ObjectError> errors;

    public InvalidParameterException() {
        super("参数验证失败");
        errors = new ArrayList<>();
    }

    public InvalidParameterException(List<ObjectError> errors) {
        this("参数验证失败", errors);
    }

    public InvalidParameterException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
