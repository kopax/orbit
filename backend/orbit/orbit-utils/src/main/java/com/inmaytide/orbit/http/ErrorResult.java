package com.inmaytide.orbit.http;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorResult implements Serializable {

    private static final long serialVersionUID = 7866421910586348125L;

    private LocalDateTime time;

    private String type;

    private String message;

    private String stackTrace;

    public ErrorResult() {
        setTime(LocalDateTime.now());
    }

    public static ErrorResult of(Throwable e) {
        return of(e, false);
    }

    public static ErrorResult of(Throwable e, boolean isOutputStrackTrace) {
        Objects.requireNonNull(e);
        ErrorResult error = new ErrorResult();
        error.setType(e.getClass().getName());
        error.setMessage(ExceptionUtils.getMessage(e));
        if (isOutputStrackTrace) {
            error.setStackTrace(ExceptionUtils.getStackTrace(e));
        }
        return error;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
