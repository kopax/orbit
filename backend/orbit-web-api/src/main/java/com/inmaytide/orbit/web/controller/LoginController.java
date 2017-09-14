package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.http.HttpHelper;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.CaptchaService;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.TokenUtils;
import com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class LoginController extends BasicController implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private CaptchaService captchaService;

    @PostMapping("login")
    @LogAnnotation(value = "系统登录", success = "登录成功", failure = "登录失败")
    public Object login(@RequestBody UsernamePasswordCaptchaToken token, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = (User) subject.getPrincipal();
        user.setToken(TokenUtils.generate(CommonUtils.uuid(), user.getUsername()));
        debug("{} login succeed.", user.getUsername());
        return RestResponse.of(user);
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response, String v) throws IOException {
        HttpHelper.disableResponseCache(response);
        response.setContentType("image/png");
        try (OutputStream os = response.getOutputStream()) {
            captchaService.generateCaptcha("png", os, v);
        }
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
