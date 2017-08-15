package com.inmaytide.orbit.service.sys;

import java.util.Set;

public interface RoleService {

    Set<String> findCodesByUsername(String username);

}
