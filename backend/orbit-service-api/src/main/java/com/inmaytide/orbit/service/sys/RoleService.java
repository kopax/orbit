package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Role;
import com.inmaytide.orbit.service.basic.BasicService;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface RoleService extends BasicService {

    String[] FINAL_FIELDS = new String[]{"id", "create_time", "creator"};

    Set<String> findCodesByUsername(String username);

    Page<Role> findList(PageModel pageModel);

    Role add(Role role);

    void remove(String ids);

    Role modify(Role role);

    Role get(Long id);
}
