package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.log.LogServiceAdapter;
import com.inmaytide.orbit.model.sys.Log;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface LogService extends LogServiceAdapter {

    Page<Log> findList(Map<String, Object> conditions, Integer pageSize, Integer pageNo);

    void export(OutputStream os, Integer type, Map<String, Object> conditions, Integer pageSize, Integer pageNo) throws IOException, InvalidFormatException;
}
