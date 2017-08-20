package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(roleRepository.findCodesByUsername(username));
    }
}
