package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.auz.helper.HttpHelper;
import com.inmaytide.orbit.auz.helper.SessionHelper;
import com.inmaytide.orbit.auz.token.UsernamePasswordCaptchaToken;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.Permission;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.CaptchaService;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.TokenUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
public class PotralController extends BasicController {

    private static final Logger log = LoggerFactory.getLogger(PotralController.class);

    @Resource
    private CaptchaService captchaService;

    @Resource
    private PermissionService permissionService;

    @PostMapping("login")
    @LogAnnotation(value = "系统登录", success = "登录成功", failure = "登录失败")
    public Mono<User> login(@RequestBody UsernamePasswordCaptchaToken token) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = (User) subject.getPrincipal();
        user.setToken(TokenUtils.generate(CommonUtils.uuid(), user.getUsername()));
        log.debug("{} login succeed.", user.getUsername());
        return Mono.create(userMonoSink -> userMonoSink.success(user));
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response, String v) throws IOException {
        HttpHelper.disableResponseCache(response);
        response.setContentType("image/png");
        try (OutputStream os = response.getOutputStream()) {
            captchaService.generateCaptcha("png", os, v);
        }
    }

    @GetMapping("/user/menus")
    @RequiresAuthentication
    public List<Permission> getMenusOfSomeone() {
        User user = SessionHelper.getCurrentUser().orElseThrow(AuthenticationException::new);
        return permissionService.findByUsername(user.getUsername());
    }

}
