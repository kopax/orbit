package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.PermissionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(permissionRepository.queryCodesByUsername(username));
    }
}