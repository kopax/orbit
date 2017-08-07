package com.inmaytide.webapp.web.controller;

import com.inmaytide.webapp.utils.LogAdapter;
import com.inmaytide.webapp.web.auth.UsernamePasswordCaptchaToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("login")
@Controller
public class LoginController implements LogAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(UsernamePasswordCaptchaToken token, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            debug("{} login succeed.", token.getUsername());
        } catch (AuthenticationException e) {
            debug("{} login failure.", token.getUsername());
            throw e;
        }
        return "redirect:/home";
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
