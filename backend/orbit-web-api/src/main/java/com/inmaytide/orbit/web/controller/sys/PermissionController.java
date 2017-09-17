package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exceptions.IllegalParameterException;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.model.sys.Permission;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.web.controller.BasicController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("sys/permission")
@RestController
public class PermissionController extends BasicController {

    @Resource
    private PermissionService service;

    @GetMapping("someones/menus")
    @RequiresAuthentication
    public RestResponse getMenusOfSomeone() {
        User user = service.getCurrentUser();
        return RestResponse.of(service.findByUsername(user.getUsername()));
    }

    @GetMapping("list")
    @RequiresPermissions("perm:list")
    public RestResponse list() {
        return RestResponse.of(service.findAllListToTree());
    }

    @DeleteMapping("delete")
    //@RequiresPermissions("perm:remove")
    @LogAnnotation("删除菜单")
    public RestResponse delete(@NotBlank String ids, BindingResult bind) {
        handleBindingResult(bind);
        service.remove(ids);
        return RestResponse.of(HttpStatus.OK);
    }

    @PutMapping("add")
    @LogAnnotation("添加菜单")
    //@RequiresPermissions("perm:add")
    public RestResponse add(@RequestBody @Validated Permission permission, BindingResult bind) {
        if (bind.hasErrors() || !service.checkCode(permission.getCode(), -1L)) {
            throw new IllegalParameterException(bind.getAllErrors());
        }
        service.add(permission);
        return RestResponse.of(permission);
    }

    @PutMapping("update")
    @LogAnnotation("修改菜单")
    public RestResponse update(@RequestBody @Validated Permission permission, BindingResult bind) {
        if (bind.hasErrors() || permission.getId() == null || !service.checkCode(permission.getCode(), permission.getId())) {
            throw new IllegalParameterException(bind.getAllErrors());
        }
        service.modify(permission);
        return RestResponse.of(permission);
    }

    @PutMapping(value = "exchangeSort", produces = "application/json")
    @LogAnnotation("修改排序")
    public RestResponse exchangeSort(@RequestBody List<Permission> permissions) {
        if (permissions.size() != 2) {
            throw new IllegalParameterException();
        }
        service.exchangeSort(permissions.toArray(new Permission[2]));
        return RestResponse.of("200", "success");
    }

    @GetMapping("checkCode/{code}/{id}")
    public RestResponse checkCode(@PathVariable String code, @PathVariable Long id) {
        return RestResponse.of(service.checkCode(code, id));
    }

}
