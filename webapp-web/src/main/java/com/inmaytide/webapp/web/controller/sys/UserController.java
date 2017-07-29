package com.inmaytide.webapp.web.controller.sys;

import com.inmaytide.webapp.model.sys.User;
import com.inmaytide.webapp.service.sys.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("sys/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("get")
    @ResponseBody
    public User get(Long id) {
        return userService.get(id);
    }

}
