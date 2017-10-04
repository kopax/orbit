package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exception.IllegalParameterException;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.domain.basic.PageModel;
import com.inmaytide.orbit.domain.sys.Role;
import com.inmaytide.orbit.service.sys.RoleService;
import com.inmaytide.orbit.web.controller.BasicController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Moss
 * @since September 10, 2017
 */
//@RestController
//@RequestMapping("sys")
public class RoleController extends BasicController {

    @Resource
    private RoleService service;

    @GetMapping("roles")
    //@RequiresPermissions("role:list")
    public Object roles(PageModel pageModel) {
        return service.findList(pageModel);
    }

    @GetMapping("role/{id}")
    public Role role(@PathVariable Long id) {
        return service.getRole(id);
    }

    @PostMapping("role")
    @LogAnnotation("新增角色")
    public Role add(@RequestBody @Validated Role role, BindingResult binding) {
        handleBindingResult(binding);
        return service.add(role);
    }

    @DeleteMapping("role")
    @LogAnnotation("删除角色")
    public void remove(String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new IllegalParameterException();
        }
        service.remove(ids);
    }

    @PutMapping("role")
    public Role modify(@RequestBody @Validated Role role, BindingResult binding) {
        if (binding.hasErrors() || Objects.isNull(role.getId())) {
            throw new IllegalParameterException();
        }
        return service.modify(role);
    }

    @GetMapping("role/checkCode/{code}/{id}")
    public Boolean checkCode(@PathVariable String code, @PathVariable Long id) {
        return service.checkCode(code, id);
    }


}
