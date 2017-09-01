package com.inmaytide.orbit.http;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorResult implements Serializable {

    private static final long serialVersionUID = 7866421910586348125L;

    private LocalDateTime time;

    private Integer code;

    private String type;

    private String message;

    public ErrorResult() {

    }

    public ErrorResult(Throwable e) {
        this.time = LocalDateTime.now();
        this.type = e.getClass().getName();
        this.message = e.getMessage();
        this.code = 1;
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
}
