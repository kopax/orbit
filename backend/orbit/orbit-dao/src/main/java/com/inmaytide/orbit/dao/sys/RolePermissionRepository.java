package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.domain.sys.RolePermission;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface RolePermissionRepository extends MybatisRepository<RolePermission, Long> {

    void deleteByRId(Long roleId);

    //void insertInBatch(List<RolePermission> rolePermissions);
}
