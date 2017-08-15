package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.UserService;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.LogAdapter;
import com.inmaytide.orbit.utils.TokenUtil;
import com.inmaytide.orbit.web.Result;
import com.inmaytide.orbit.web.auth.UsernamePasswordCaptchaToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController extends BasicController implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @PostMapping("login")
    public Object login(@RequestBody UsernamePasswordCaptchaToken token, HttpServletResponse response) {

        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = userService.findByUsername(token.getUsername()).orElseGet(User::new);
        user.setToken(TokenUtil.generate(CommonUtils.uuid(), user.getUsername()));

        return Result.ofSuccess(user, "login succeed.");
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
