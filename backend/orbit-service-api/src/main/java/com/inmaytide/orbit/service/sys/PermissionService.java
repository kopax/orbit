package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.Permission;
import org.springframework.data.support.CrudService;

import java.util.List;
import java.util.Set;

public interface PermissionService extends CrudService<Permission, Long> {

    Set<String> findCodesByUsername(String username);

    Permission add(Permission inst);

    List<Permission> findMenusByUsername(String username);

    List<Permission> findList();
}
