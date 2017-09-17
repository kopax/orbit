package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.exceptions.IllegalParameterException;
import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Role;
import com.inmaytide.orbit.service.sys.RoleService;
import com.inmaytide.orbit.web.controller.BasicController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Moss
 * @since September 10, 2017
 */
@RestController
@RequestMapping("sys")
public class RoleController extends BasicController {

    @Resource
    private RoleService service;

    @GetMapping("roles")
    //@RequiresPermissions("role:list")
    public RestResponse roles(PageModel pageModel) {
        return RestResponse.of(service.findList(pageModel));
    }

    @GetMapping("role/{id}")
    public RestResponse role(@PathVariable Long id) {
        return RestResponse.of(service.get(id));
    }

    @PostMapping("role")
    public RestResponse add(@RequestBody @Validated Role role, BindingResult binding) {
        handleBindingResult(binding);
        return RestResponse.of(service.add(role));
    }

    @DeleteMapping("role")
    public RestResponse remove(String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new IllegalParameterException();
        }
        service.remove(ids);
        return RestResponse.of(HttpStatus.OK);
    }

    @PutMapping("role")
    public RestResponse modify(@Validated Role role, BindingResult binding) {
        if (binding.hasErrors() || Objects.isNull(role.getId())) {
            throw new IllegalParameterException();
        }
        return RestResponse.of(service.modify(role));
    }

    @GetMapping("role/checkCode/{code}/{id}")
    public RestResponse checkCode(@PathVariable String code, @PathVariable Long id) {
        return RestResponse.of(service.checkCode(code, id));
    }



}
