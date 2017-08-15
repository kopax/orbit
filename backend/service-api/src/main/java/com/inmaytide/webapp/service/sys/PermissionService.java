package com.inmaytide.webapp.service.sys;

import java.util.Set;

public interface PermissionService {

    Set<String> findCodesByUsername(String username);

}
