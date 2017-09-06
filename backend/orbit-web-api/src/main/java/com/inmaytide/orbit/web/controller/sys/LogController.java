package com.inmaytide.orbit.web.controller.sys;

import com.google.common.collect.Maps;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.service.sys.LogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("sys/log")
public class LogController {

    @Resource
    private LogService service;

    @GetMapping("list")
    public RestResponse list(String keywords, String begin, String end, Integer pageSize, Integer pageNo) {
        Map<String, Object> conditions = Maps.newHashMap();
        if (StringUtils.isNotBlank(keywords)) {
            conditions.put("keywords", keywords);
        }
        if (StringUtils.isNotBlank(begin)) {
            conditions.put("begin", begin);
        }
        if (StringUtils.isNotBlank(end)) {
            conditions.put("end", end);
        }
        return RestResponse.of(service.findList(conditions, pageSize, pageNo));
    }

}
