package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.auz.provider.RoleProvider;
import com.inmaytide.orbit.domain.basic.PageModel;
import com.inmaytide.orbit.domain.sys.Role;
import com.inmaytide.orbit.service.basic.BasicService;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface RoleService extends BasicService, RoleProvider {

    String[] FINAL_FIELDS = new String[]{"id", "createTime", "creator"};

    Page<Role> findList(PageModel pageModel);

    Role add(Role role);

    void remove(String ids);

    Role modify(Role role);

    Role getRole(Long id);

    Boolean checkCode(String code, Long id);
}
