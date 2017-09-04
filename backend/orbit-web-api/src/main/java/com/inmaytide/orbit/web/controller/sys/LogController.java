package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.service.sys.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("sys/log")
public class LogController {

    @Resource
    private LogService service;

    @GetMapping("list")
    public RestResponse list(Log log, Integer pageSize, Integer pageNo) {
        return RestResponse.of(service.findList(log, pageSize, pageNo));
    }

}
