package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.auz.helper.SessionHelper;
import com.inmaytide.orbit.dao.sys.LogRepository;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Log;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.IdGenerator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl extends AbstractCrudService<LogRepository, Log, Long> implements LogService {

    private static final String LOGIN_METHOD_NAME = "login";

    private static final String LOGIN_CONTROLLER_NAME = "com.inmaytide.orbit.web.controller.LoginController";

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }

    @Override
    public Page<Log> findList(Map<String, Object> conditions, PageModel pageModel) {
        Pageable pageable = pageModel.toPageable(Sort.Direction.DESC, "time");
        conditions.put("size", Integer.valueOf(pageable.getPageSize()));
        conditions.put("offset", pageable.getOffset());
        List<Log> content = getRepository().findList(conditions);
        return new PageImpl<>(content, pageable, getRepository().findCount(conditions));
    }

    @Override
    public List<Log> findList(Map<String, Object> conditions) {
        return getRepository().findList(conditions);
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void record(JoinPoint point, Throwable e) {
        Log inst = createInstance(point, false);
        inst.setMessage(String.format("%s => %s", e.getClass().getName(), e.getMessage()));
        if (isLoggingIn(point)) {
            UsernamePasswordToken token = (UsernamePasswordToken) point.getArgs()[0];
            inst.setMessage(String.format("%s, username => %s", inst.getMessage(), token.getUsername()));
        }
        insert(inst);
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void record(JoinPoint point) {
        insert(createInstance(point, true));
    }

    private Log createInstance(JoinPoint point, boolean isSucceed) {
        LogAnnotation annotation = getLogAnnotation(point);
        Log inst = new Log();
        inst.setId(IdGenerator.getInstance().nextId());
        inst.setName(annotation.value());
        inst.setOperator(SessionHelper.getCurrentUser().orElse(new User()).getId());
        inst.setMethodName(point.getSignature().getName());
        inst.setClassName(point.getSignature().getDeclaringTypeName());
        inst.setIpAddress(SessionHelper.getSession().getHost());
        inst.setIsSucceed(isSucceed ? annotation.success() : annotation.failure());
        return inst;
    }

    private boolean isLoggingIn(JoinPoint point) {
        return LOGIN_METHOD_NAME.equals(point.getSignature().getName())
                && LOGIN_CONTROLLER_NAME.equals(point.getSignature().getDeclaringTypeName());
    }
}
