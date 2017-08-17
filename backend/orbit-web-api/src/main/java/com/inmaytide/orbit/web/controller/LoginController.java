package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.UserService;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.LogAdapter;
import com.inmaytide.orbit.utils.TokenUtil;
import com.inmaytide.orbit.model.basic.Result;
import com.inmaytide.orbit.web.auth.SessionHelper;
import com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class LoginController extends BasicController implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @Resource
    private ConfigurableCaptchaService configurableCaptchaService;

//    @PostMapping("login")
//    public Object login(@RequestBody UsernamePasswordCaptchaToken token, HttpServletResponse response) {
//
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(token);
//        User user = userService.findByUsername(token.getUsername()).orElseGet(User::new);
//        user.setToken(TokenUtil.generate(CommonUtils.uuid(), user.getUsername()));
//
//        return Result.ofSuccess(user, "login succeed.");
//    }

    @RequestMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        CommonUtils.disableResponseCache(response);
        response.setContentType("image/png");
        try (OutputStream os = response.getOutputStream()) {
            String words = EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService, "png", os);
            SessionHelper.setAttribute(Constants.SESSION_CAPTCHA_KEY, words);
        }
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
