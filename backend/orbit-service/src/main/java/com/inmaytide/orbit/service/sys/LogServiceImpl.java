package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.consts.LogCategory;
import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.Log;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService, LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    private UserService userService;

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

    @Override
    public void record(JoinPoint point, Throwable e) {
        LogAnnotation annotation = getLogAnnotation(point);
        if ("login".equals(point.getSignature().getName())) {
            loginFailedRecord(point, e);
        } else {
            Log inst = Log.of();
            String content = String.format("%s => %s", annotation.description(), annotation.failure());
            inst.setCategory(LogCategory.FAILED.getCode());
            inst.setContent(content);
            inst.setOperator(userService.getCurrent().getId());
            inst.setDetails(e.getClass().getName() + " => " + e.getMessage());
            save(inst);
        }

    }

    @Override
    public void record(JoinPoint point) {
        Log inst = Log.of();
        LogAnnotation annotation = getLogAnnotation(point);
        String content = String.format("%s => %s", annotation.description(), annotation.success());
        inst.setCategory(LogCategory.SUCCED.getCode());
        inst.setContent(content);
        inst.setOperator(userService.getCurrent().getId());
        save(inst);
    }


    private void loginFailedRecord(JoinPoint point, Throwable e) {
        Log inst = Log.of();
        LogAnnotation annotation = getLogAnnotation(point);
        inst.setCategory(LogCategory.FAILED.getCode());
        UsernamePasswordToken token = (UsernamePasswordToken) point.getArgs()[0];
        String content = String.format("%s => %s, username => %s",
                annotation.description(),
                annotation.failure(),
                token.getUsername());
        inst.setContent(content);
        inst.setDetails(e.getClass().getName() + " => " + e.getMessage());
        save(inst);
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
