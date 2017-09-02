package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.Permission;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.service.sys.UserService;
import com.inmaytide.orbit.web.controller.BasicController;
import com.inmaytide.orbit.web.exception.InvalidParameterException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("sys/permission")
@RestController
public class PermissionController extends BasicController {

    @Resource
    private PermissionService service;

    @Resource
    private UserService userService;

    @GetMapping("someones/menus")
    @RequiresAuthentication
    public RestResponse getMenusOfSomeone() {
        User user = userService.getCurrent();
        return RestResponse.of(service.findMenusByUsername(user.getUsername()));
    }

    @GetMapping("list")
    @RequiresPermissions("perm:list")
    public RestResponse list() {
        return RestResponse.of(service.findList());
    }

    @DeleteMapping("delete")
    //@RequiresPermissions("perm:delete")
    @LogAnnotation("删除菜单")
    public RestResponse delete(@RequestBody Long[] ids) {
        service.deleteBatch(ids);
        return RestResponse.of("200", "菜单删除成功");
    }

    @PutMapping("add")
    @LogAnnotation("添加菜单")
    @RequiresPermissions("perm:add")
    public RestResponse add(@RequestBody @Validated Permission permission, BindingResult bind) {
        if (bind.hasErrors() || !service.checkCode(permission.getCode(), -1L)) {
            throw new InvalidParameterException(bind.getAllErrors());
        }
        service.add(permission);
        return RestResponse.of(permission);
    }

    @GetMapping("checkCode/{code}/{id}")
    public RestResponse checkCode(@PathVariable String code, @PathVariable Long id) {
        return RestResponse.of(service.checkCode(code, id));
    }

}
