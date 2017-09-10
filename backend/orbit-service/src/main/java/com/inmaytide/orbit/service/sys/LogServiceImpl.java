package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.office.excel.ExcelExportHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService, LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    private UserService userService;

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

    public Page<Log> findList(Map<String, Object> conditions, PageModel pageModel) {
        Pageable pageable = pageModel.toPageable(Sort.Direction.DESC, "logTime");
        conditions.put("size", pageable.getPageSize());
        conditions.put("offset", pageable.getOffset());
        conditions.put("ispagation", 1);
        List<Log> content = getRepository().findList(conditions);
        return new PageImpl<Log>(content, pageable, getRepository().findCount(conditions));
    }

    @Override
    public void export(OutputStream os, Map<String, Object> conditions) throws IOException, InvalidFormatException {
        conditions.put("ispagation", "0");
        List<Log> content = getRepository().findList(conditions);
        ExcelExportHelper.export(Log.class, content, os);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
