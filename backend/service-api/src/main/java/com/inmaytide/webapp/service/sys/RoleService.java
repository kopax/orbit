package com.inmaytide.webapp.service.sys;

import java.util.Set;

public interface RoleService {

    Set<String> findCodesByUsername(String username);

}
