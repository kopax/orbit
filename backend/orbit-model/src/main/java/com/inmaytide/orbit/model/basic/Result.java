package com.inmaytide.orbit.model.basic;

import com.inmaytide.orbit.consts.Constants;

public class Result implements java.io.Serializable {

    private String status;

    private Object data;

    private String message;

    public Result() {

    }

    public Result(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }



    public static Result ofSuccess(Object data, String message) {
        return new Result(Constants.REQUEST_STATUS_SUCCESS, data, message);
    }

    public static Result ofFail(Object data, String message) {
        return new Result(Constants.REQUEST_STATUS_FAIL, data, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
