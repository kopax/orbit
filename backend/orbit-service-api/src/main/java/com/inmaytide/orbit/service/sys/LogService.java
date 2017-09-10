package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.log.LogServiceAdapter;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Log;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface LogService extends LogServiceAdapter {

    Page<Log> findList(Map<String, Object> conditions, PageModel pageModel);

    void export(OutputStream os, Map<String, Object> conditions) throws IOException, InvalidFormatException;
}
