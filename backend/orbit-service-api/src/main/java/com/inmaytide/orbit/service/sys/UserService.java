package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.User;
import org.springframework.data.support.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> findByUsername(String username);

}
