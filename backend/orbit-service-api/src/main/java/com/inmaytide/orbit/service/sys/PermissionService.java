package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.Permission;
import org.springframework.data.support.CrudService;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    String[] FINAL_FIELDS = new String[]{"id", "parent", "create_time", "creator", "sort"};

    Set<String> findCodesByUsername(String username);

    Permission add(Permission inst);

    Permission modify(Permission inst);

    List<Permission> findMenusByUsername(String username);

    List<Permission> findList();

    void deleteBatch(Long[] ids);

    Boolean checkCode(String code, Long id);

    void exchangeSort(Permission[] permissions);
}
