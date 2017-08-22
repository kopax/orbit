package com.inmaytide.orbit.web.auth;

import com.inmaytide.orbit.model.sys.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Optional;

public class SessionHelper {

    public static Optional<Subject> getSubject() {
        return Optional.ofNullable(SecurityUtils.getSubject());
    }

    public static Session getSession() {
        return getSubject().map(Subject::getSession).orElseThrow(RuntimeException::new);
    }

    public static <T> Optional<T> getSessionAttribute(String key) {
        return Optional.ofNullable((T) getSession().getAttribute(key));
    }

    public static void setAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static User getCurrentUser() {
        //获取不到用户信息
        Subject subject = getSubject().orElseThrow(AuthenticationException::new);
        return (User) subject.getPrincipal();
    }

}
