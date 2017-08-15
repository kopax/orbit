package com.inmaytide.orbit.sys.dao;

import com.inmaytide.orbit.sys.model.sys.Permission;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface PermissionRepository extends MybatisRepository<Permission, Long> {

    @Query("select distinct(p.code) code from sys_permission p " +
            "left join sys_role_permission rp on rp.p_id = p.id " +
            "left join sys_role r on rp.r_id = r.id " +
            "left join sys_user_role ur on ur.r_id = r.id " +
            "left join sys_user u on u.id = ur.u_id " +
            "where u.username = ?1 ")
    List<String> queryCodesByUsername(String username);

}
