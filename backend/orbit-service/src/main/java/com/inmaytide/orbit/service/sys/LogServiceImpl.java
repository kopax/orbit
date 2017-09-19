package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.office.excel.ExcelExportHelper;
import com.inmaytide.orbit.utils.IdGenerator;
import com.inmaytide.orbit.utils.SessionHelper;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

    public Page<Log> findList(Map<String, Object> conditions, PageModel pageModel) {
        Pageable pageable = pageModel.toPageable(Sort.Direction.DESC, "time");
        conditions.put("size", pageable.getPageSize());
        conditions.put("offset", pageable.getOffset());
        conditions.put("ispagation", 1);
        List<Log> content = getRepository().findList(conditions);
        return new PageImpl<>(content, pageable, getRepository().findCount(conditions));
    }

    @Override
    public void export(OutputStream os, Map<String, Object> conditions) throws IOException, InvalidFormatException {
        conditions.put("ispagation", "0");
        List<Log> content = getRepository().findList(conditions);
        ExcelExportHelper.export(Log.class, content, os);
    }

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void record(JoinPoint point, Throwable e) {
        LogAnnotation annotation = getLogAnnotation(point);
        if ("login".equals(point.getSignature().getName())) {
            loginFailedRecord(point, e);
        } else {
            Log inst = buildLogFromJoinPoint(point);
            inst.setIsSucceed(annotation.failure());
            inst.setMessage(e.getClass().getName() + " => " + e.getMessage());
            insert(inst);
        }

    }

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void record(JoinPoint point) {
        Log inst = buildLogFromJoinPoint(point);
        LogAnnotation annotation = getLogAnnotation(point);
        inst.setIsSucceed(annotation.success());
        insert(inst);
    }


    private void loginFailedRecord(JoinPoint point, Throwable e) {
        Log inst = buildLogFromJoinPoint(point);
        LogAnnotation annotation = getLogAnnotation(point);
        UsernamePasswordToken token = (UsernamePasswordToken) point.getArgs()[0];
        inst.setIsSucceed(annotation.failure());
        inst.setMessage(e.getClass().getName() + " => " + e.getMessage() + ", username => " + token.getUsername());
        insert(inst);
    }

    private Log buildLogFromJoinPoint(JoinPoint point) {
        LogAnnotation annotation = getLogAnnotation(point);
        Log inst = new Log();
        inst.setId(IdGenerator.getInstance().nextId());
        inst.setName(annotation.value());
        inst.setOperator(getCurrentUser().getId());
        inst.setMethodName(point.getSignature().getName());
        inst.setClassName(point.getSignature().getDeclaringTypeName());
        inst.setIpAddress(SessionHelper.getSession().getHost());
        return inst;
    }
}
