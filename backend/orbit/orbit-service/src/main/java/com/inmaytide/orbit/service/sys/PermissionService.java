package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.auz.provider.PermissionProvider;
import com.inmaytide.orbit.domain.sys.Permission;
import com.inmaytide.orbit.service.basic.BasicService;

import java.util.List;
import java.util.Set;

public interface PermissionService extends BasicService, PermissionProvider {

    String[] FINAL_FIELDS = new String[]{"id", "parent", "create_time", "creator", "sort"};

    List<Permission> findByRoleIds(final Long[] roleIds);

    Permission add(Permission inst);

    Permission modify(Permission inst);

    List<Permission> findByUsername(String username);

    List<Permission> findAllListToTree();

    void remove(String ids);

    Boolean checkCode(String code, Long id);

    void exchangeSort(Permission[] permissions);
}
