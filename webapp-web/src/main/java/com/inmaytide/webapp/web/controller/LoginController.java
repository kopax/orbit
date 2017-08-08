package com.inmaytide.webapp.web.controller;

import com.inmaytide.webapp.model.sys.User;
import com.inmaytide.webapp.service.sys.UserService;
import com.inmaytide.webapp.utils.CommonUtils;
import com.inmaytide.webapp.utils.LogAdapter;
import com.inmaytide.webapp.utils.TokenUtil;
import com.inmaytide.webapp.web.auth.UsernamePasswordCaptchaToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @RequestMapping("login")
    public Object login(UsernamePasswordCaptchaToken token, HttpServletResponse response) {
        response.addHeader( "Access-Control-Allow-Origin", "*" );
        response.addHeader( "Access-Control-Allow-Methods", "POST" );
        response.addHeader( "Access-Control-Max-Age", "1000" );

        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        User user = userService.findByUsername(token.getUsername()).orElseGet(User::new);
        user.setToken(TokenUtil.generate(CommonUtils.uuid(), user.getUsername()));

        return user;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
