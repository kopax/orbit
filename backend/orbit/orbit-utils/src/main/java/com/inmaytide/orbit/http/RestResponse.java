package com.inmaytide.orbit.http;

import com.inmaytide.orbit.utils.CommonUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RestResponse implements Serializable {

    private static final long serialVersionUID = -3039241497836831014L;

    private String id;

    private String code;

    private String message;

    private Object data;

    private ErrorResult error;

    public RestResponse() {
        this.id = CommonUtils.uuid();
    }

    public static RestResponse of(HttpStatus httpStatus) {
        return of(Integer.toString(HttpStatus.OK.value()), null);
    }

    public static RestResponse of(Object data) {
        return of(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static RestResponse of(HttpStatus httpStatus, ErrorResult error) {
        return of(Integer.toString(httpStatus.value()), httpStatus.getReasonPhrase(), error);
    }

    public static RestResponse of(String code, String message, ErrorResult error) {
        RestResponse response = of(code, message);
        response.setError(error);
        return response;
    }

    public static RestResponse of(String code, String message) {
        RestResponse response = new RestResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static RestResponse of(String code, String message, Object data) {
        RestResponse response = of(code, message);
        response.setData(data);
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ErrorResult getError() {
        return error;
    }

    public void setError(ErrorResult error) {
        this.error = error;
    }
}
