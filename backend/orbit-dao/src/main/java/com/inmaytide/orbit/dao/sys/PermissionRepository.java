package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Permission;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends MybatisRepository<Permission, Long> {

    @Query(value = "findCodesByUsername")
    List<String> findCodesByUsername(@Param("username") String username);

    @Query(value = "findByUsername")
    List<Permission> findByUsername(@Param("username") String username, @Param("category") String category);

    void deleteByIdIn(@Param("ids") Long[] ids);

    @Query(value = "getSort")
    Integer getSort();

    Integer countByCodeAndIdNot(String code, Long id);

    @Query(value = "findByRoleIds")
    List<Permission> findByRoleIds(@Param("roleIds") Long[] roleIds);
}
