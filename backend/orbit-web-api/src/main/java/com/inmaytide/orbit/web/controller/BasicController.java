package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.web.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BasicController {

    @ExceptionHandler
    @ResponseBody
    public Object exception(Exception e) {

        return Result.ofFail(null, e.getMessage());
    }

}
