package com.inmaytide.orbit.service.sys;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.model.sys.Log;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService {
    /**
     * 构造函数.
     *
     * @param repository 注入的Repository
     */
    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

}
