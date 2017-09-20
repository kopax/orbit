package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.basic.BasicService;
import org.springframework.data.support.CrudService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService extends BasicService {

    Optional<User> findByUsername(String username);

    List<User> findByRoleIds(Long[] roleIds);

}
