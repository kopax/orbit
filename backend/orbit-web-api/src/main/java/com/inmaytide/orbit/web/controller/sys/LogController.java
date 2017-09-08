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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("sys/log")
public class LogController {

    @Resource
    private LogService service;

    private Map<String, Object> buildConditions(String keywords, String begin, String end) {
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
        return conditions;
    }

    @GetMapping("list")
    public RestResponse list(Integer pageNo, Integer pageSize, String keywords, String begin, String end) throws IOException {
        return RestResponse.of(service.findList(buildConditions(keywords, begin, end), pageSize, pageNo));
    }

    @GetMapping("export")
    public void export(HttpServletResponse response, Integer pageNo, Integer pageSize,
                       String keywords, String begin, String end, Integer type) throws IOException, InvalidFormatException {
        Map<String, Object> conditions = buildConditions(keywords, begin, end);
        response.setContentType("application/octet-stream");

        try (OutputStream os = response.getOutputStream()) {
            service.export(os, type, conditions, pageSize, pageNo);
        }

    }

}
