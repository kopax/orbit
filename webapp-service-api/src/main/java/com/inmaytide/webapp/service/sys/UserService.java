package com.inmaytide.webapp.service.sys;

import com.inmaytide.webapp.model.sys.User;

import java.util.Optional;

public interface UserService {

    Optional<User> get(Long id);

    Optional<User> findByUsername(String username);

}
