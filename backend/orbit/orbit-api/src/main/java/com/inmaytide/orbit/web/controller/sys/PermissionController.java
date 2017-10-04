package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exception.IllegalParameterException;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.domain.sys.Permission;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.web.controller.BasicController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("sys")
//@RestController
public class PermissionController extends BasicController {

    @Resource
    private PermissionService service;

    @GetMapping("permissions")
    @RequiresPermissions("perm:list")
    public List<Permission> list() {
        return service.findAllListToTree();
    }

    @DeleteMapping("permissions/{ids}")
    @LogAnnotation("删除菜单")
    public void remove(String ids) {
        requireNotBlank(ids);
        service.remove(ids);
    }

    @PostMapping("permissions")
    @LogAnnotation("添加菜单")
    public Permission add(@RequestBody @Validated Permission permission, BindingResult bind) {
        if (bind.hasErrors() || !service.checkCode(permission.getCode(), -1L)) {
            throw new IllegalParameterException();
        }
        return service.add(permission);
    }

    @PutMapping("permissions/{id}")
    @LogAnnotation("修改菜单")
    public Permission update(@RequestBody @Validated Permission permission, BindingResult bind) {
        if (bind.hasErrors() || permission.getId() == null || !service.checkCode(permission.getCode(), permission.getId())) {
            throw new IllegalParameterException();
        }
        return service.modify(permission);
    }

    @PatchMapping(value = "exchangeSort")
    @LogAnnotation("修改排序")
    public void exchangeSort(@RequestBody List<Permission> permissions) {
        if (permissions.size() != 2) {
            throw new IllegalParameterException();
        }
        service.exchangeSort(permissions.toArray(new Permission[2]));
    }

    @GetMapping("checkCode/{code}/{id}")
    public Boolean checkCode(@PathVariable String code, @PathVariable Long id) {
        return service.checkCode(code, id);
    }

}
