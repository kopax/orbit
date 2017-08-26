package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.model.basic.Result;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.service.sys.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("sys/permission")
public class PermissionController {

    @Resource
    private PermissionService service;

    @Resource
    private UserService userService;

    @GetMapping("get/someones/menus")
    public Result getMenusOfSomeone() {
        User user = userService.getCurrent();
        return Result.ofSuccess(service.findMenusByUsername(user.getUsername()));
    }

}
