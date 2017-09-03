package com.inmaytide.orbit.exceptions;

import org.springframework.http.HttpStatus;

public enum ResponseErrorCodes {

    VersionMatched("1001"),
    InvalidParameter("1002");

    private String code;

    private ResponseErrorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
