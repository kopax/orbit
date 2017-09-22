package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.log.LogServiceAdapter;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.service.basic.BasicService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LogService extends LogServiceAdapter, BasicService {

    Page<Log> findList(Map<String, Object> conditions, PageModel pageModel);

    List<Log> findList(Map<String, Object> conditions);

}
