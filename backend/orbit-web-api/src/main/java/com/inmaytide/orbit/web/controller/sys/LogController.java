package com.inmaytide.orbit.web.controller.sys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.inmaytide.orbit.exceptions.InvalidParameterException;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.service.sys.LogService;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("sys/log")
public class LogController {

    @Resource
    private LogService service;

    @GetMapping("list")
    public RestResponse list(Integer pageNo, Integer pageSize, String keywords, String begin, String end) throws IOException {
        Map<String, Object> conditions = Maps.newHashMap();
        if (StringUtils.isNotBlank(keywords)) {
            conditions.put("keywords", keywords);
        }
        try {
            if (StringUtils.isNotBlank(begin)) {
                conditions.put("begin", DateTimeUtils.format(begin, 0, 0, 0, "yyyy-MM-dd HH:mm:ss"));
            }
            if (StringUtils.isNotBlank(end)) {
                conditions.put("end", DateTimeUtils.format(end, 23, 59, 59, "yyyy-MM-dd HH:mm:ss"));
            }
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
        return RestResponse.of(service.findList(conditions, pageSize, pageNo));
    }

}
