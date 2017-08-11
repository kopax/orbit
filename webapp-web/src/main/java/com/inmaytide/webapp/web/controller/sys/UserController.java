package com.inmaytide.webapp.web.controller.sys;

import com.inmaytide.webapp.model.sys.User;
import com.inmaytide.webapp.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("sys/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserService userService;

    @GetMapping
    @ResponseBody
    public User get(Long id) {
        logger.info("id {}", id);
        return userService.get(id).orElseGet(User::new);
    }

}