package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@RestController
//@RequestMapping("sys/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserService userService;


}
