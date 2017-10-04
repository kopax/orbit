package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.auz.provider.UserProvider;
import com.inmaytide.orbit.domain.sys.User;
import com.inmaytide.orbit.service.basic.BasicService;

import java.util.List;

public interface UserService extends BasicService, UserProvider {

    List<User> findByRoleIds(Long[] roleIds);

}
