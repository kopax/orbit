package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.log.LogServiceAdapter;
import com.inmaytide.orbit.model.sys.Log;
import org.springframework.data.domain.Page;

public interface LogService extends LogServiceAdapter {

    Page<Log> findList(Log conditions, Integer pageSize, Integer pageNo);

}
