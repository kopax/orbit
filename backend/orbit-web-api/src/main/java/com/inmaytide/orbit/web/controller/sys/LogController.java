package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exceptions.IllegalParameterException;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.service.sys.LogService;
import com.inmaytide.orbit.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("sys/log")
@Controller
public class LogController {

    private static final LocalTime SEARCH_DEFAULT_BEGIN_TIME = LocalTime.of(0, 0, 0);
    private static final LocalTime SEARCH_DEFAULT_END_TIME = LocalTime.of(23, 59, 59);

    @Resource
    private LogService service;

    private Map<String, Object> buildConditions(String keywords, String begin, String end) {
        Map<String, Object> conditions = new HashMap<>();
        if (StringUtils.isNotBlank(keywords)) {
            conditions.put("keywords", keywords);
        }
        try {
            if (StringUtils.isNotBlank(begin)) {
                conditions.put("begin", DateTimeUtils.format(begin, SEARCH_DEFAULT_BEGIN_TIME, "yyyy-M-d", "yyyy-MM-dd HH:mm:ss"));
            }
            if (StringUtils.isNotBlank(end)) {
                conditions.put("end", DateTimeUtils.format(end, SEARCH_DEFAULT_END_TIME, "yyyy-M-d", "yyyy-MM-dd HH:mm:ss"));
            }
        } catch (Exception e) {
            throw new IllegalParameterException();
        }
        return conditions;
    }

    @GetMapping("list")
    @ResponseBody
    public RestResponse list(PageModel pageModel, String begin, String end) throws IOException {
        return RestResponse.of(service.findList(buildConditions(pageModel.getKeywords(false), begin, end), pageModel));
    }

    @GetMapping("export")
    @LogAnnotation("导出日志")
    public void export(HttpServletResponse response,
                       String keywords, String begin, String end) throws IOException, InvalidFormatException {
        Map<String, Object> conditions = buildConditions(keywords, begin, end);
        response.setContentType("application/octet-stream");
        String filename = new String("日志列表".getBytes("utf-8"), "iso-8859-1") + ".xlsx";
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);

        try (OutputStream os = response.getOutputStream()) {
            service.export(os, conditions);
        }

    }

}