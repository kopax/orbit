package com.inmaytide.orbit.sys.service;

import java.util.Set;

public interface PermissionService {

    Set<String> findCodesByUsername(String username);

}
