package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.model.sys.User;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService, LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    private UserService userService;

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

    public Page<Log> findList(Map<String, Object> conditions, Integer pageSize, Integer pageNo) {
        Integer size = pageSize == null ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        conditions.put("size", size);
        conditions.put("offset", pageNo == null ? 0 : (pageNo - 1) * size);
        List<Log> content = getRepository().findList(conditions);
        Sort sort = new Sort(Sort.Direction.DESC, "logTime");
        Pageable pageable = new PageRequest(pageNo == null ? 0 : pageNo - 1, size, sort);
        return new PageImpl<Log>(content, pageable, getRepository().findCount(conditions));
    }

    @Override
    public void record(JoinPoint point, Throwable e) {
        LogAnnotation annotation = getLogAnnotation(point);
        if ("login".equals(point.getSignature().getName())) {
            loginFailedRecord(point, e);
        } else {
            Log inst = Log.of();
            String content = String.format("%s => %s", annotation.value(), annotation.failure());
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
        String content = String.format("%s => %s", annotation.value(), annotation.success());
        inst.setContent(content);
        inst.setOperator(userService.getCurrent().getId());
        save(inst);
    }


    private void loginFailedRecord(JoinPoint point, Throwable e) {
        Log inst = Log.of();
        LogAnnotation annotation = getLogAnnotation(point);
        UsernamePasswordToken token = (UsernamePasswordToken) point.getArgs()[0];
        String content = String.format("%s => %s, username => %s",
                annotation.value(),
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
