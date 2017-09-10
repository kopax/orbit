package com.inmaytide.orbit.web.controller.sys;

import com.inmaytide.orbit.http.RestResponse;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.service.sys.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Moss
 * @since September 10, 2017
 */
@RestController
@RequestMapping("sys/role")
public class RoleController {

    @Resource
    private RoleService service;

    @GetMapping("list")
    //@RequiresPermissions("role:list")
    public RestResponse list(PageModel pageModel) {
        return RestResponse.of(service.findList(pageModel));
    }

}
