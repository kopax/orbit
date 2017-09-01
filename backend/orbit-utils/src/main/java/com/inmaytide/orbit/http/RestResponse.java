package com.inmaytide.orbit.http;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.utils.CommonUtils;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = -3039241497836831014L;

    private String id;

    private ResponseState state;

    private String message;

    private T data;

    private ErrorResult error;

    public RestResponse() {
        this.id = CommonUtils.uuid();
    }

    public static <T> RestResponse<T> ofSuccess(T data, String message) {
        RestResponse<T> response = new RestResponse<T>();
        response.setData(data);
        response.setMessage(message);
        response.setState(ResponseState.SUCCESS);
        return response;
    }

    public static <T> RestResponse<T> ofFail(String message, Throwable e) {
        RestResponse<T> response = new RestResponse<T>();
        response.setMessage(message);
        response.setState(ResponseState.FAIL);
        response.setError(new ErrorResult(e));
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResponseState getState() {
        return state;
    }

    public void setState(ResponseState state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResult getError() {
        return error;
    }

    public void setError(ErrorResult error) {
        this.error = error;
    }
}
