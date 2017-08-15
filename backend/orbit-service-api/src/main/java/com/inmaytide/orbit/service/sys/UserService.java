package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.User;

import java.util.Optional;

public interface UserService {

    Optional<User> get(Long id);

    Optional<User> findByUsername(String username);

}
