package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exception.IllegalParameterException;
import com.inmaytide.orbit.i18n.I18nMessages;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.domain.basic.PageModel;
import com.inmaytide.orbit.domain.sys.Log;
import com.inmaytide.orbit.office.excel.ExcelExportHelper;
import com.inmaytide.orbit.service.sys.LogService;
import com.inmaytide.orbit.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("sys")
//@RestController
public class LogController {

    private static final LocalTime SEARCH_DEFAULT_BEGIN_TIME = LocalTime.of(0, 0, 0);
    private static final LocalTime SEARCH_DEFAULT_END_TIME = LocalTime.of(23, 59, 59);

    @Resource
    private LogService service;

    @Resource
    private I18nMessages i18n;

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

    @GetMapping("logs")
    public Object list(PageModel pageModel, String begin, String end) throws IOException {
        Map<String, Object> conditions = buildConditions(pageModel.getKeywords(false), begin, end);
        return service.findList(conditions, pageModel);
    }

//    @GetMapping("logs/as-excel")
//    @LogAnnotation("导出日志")
//    public void export(HttpServletResponse response,
//                       String keywords, String begin, String end) throws IOException, InvalidFormatException {
//        Map<String, Object> conditions = buildConditions(keywords, begin, end);
//        response.setContentType("application/octet-stream");
//        String filename = new String(i18n.get("log.export.file.name", "日志列表").getBytes("utf-8"), "iso-8859-1") + ".xlsx";
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//
//        try (OutputStream os = response.getOutputStream()) {
//            ExcelExportHelper.export(Log.class, service.findList(conditions), os);
//        }
//
//    }

}