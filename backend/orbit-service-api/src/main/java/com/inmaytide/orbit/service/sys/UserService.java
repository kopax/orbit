package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.User;
import org.springframework.data.support.CrudService;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User getCurrent();

    Map<Long, User> getAllUser();

}
