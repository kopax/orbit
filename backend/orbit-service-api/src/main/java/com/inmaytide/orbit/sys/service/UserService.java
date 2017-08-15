package com.inmaytide.orbit.sys.service;

import com.inmaytide.orbit.sys.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> get(Long id);

    Optional<User> findByUsername(String username);

}
