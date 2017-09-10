package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Role;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface RoleService {

    Set<String> findCodesByUsername(String username);

    Page<Role> findList(PageModel pageModel);
}
