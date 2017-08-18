package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.model.basic.Result;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("sys/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserService userService;

    @GetMapping("get/{id}")
    @RequiresPermissions("user:get")
    public Object get(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request) {
        //response.setHeader("Access-control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        //response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        logger.info("id {}", id);
        return Result.ofSuccess(userService.get(id), null);
    }

}
