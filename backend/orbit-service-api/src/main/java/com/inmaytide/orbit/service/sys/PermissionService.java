package com.inmaytide.orbit.service.sys;

import java.util.Set;

public interface PermissionService {

    Set<String> findCodesByUsername(String username);

}
