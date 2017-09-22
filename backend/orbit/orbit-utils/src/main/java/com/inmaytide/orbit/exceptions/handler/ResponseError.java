package com.inmaytide.orbit.exceptions.handler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseError implements Serializable {

    private Integer code;

    private String type;

    private String message;

    public static ResponseError of(Throwable e) {
        ResponseError error = new ResponseError();
        error.setType(e.getClass().getName());
        error.setMessage(e.getMessage());
        return error;
    }

    public static ResponseError of(HttpStatus status, String message) {
        ResponseError error = new ResponseError();
        error.setCode(status.value());
        error.setMessage(message);
        return error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
