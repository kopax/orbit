package com.inmaytide.orbit.sys.service;

import java.util.Set;

public interface RoleService {

    Set<String> findCodesByUsername(String username);

}
