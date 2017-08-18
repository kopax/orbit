package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Role;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface RoleRepository extends MybatisRepository<Role, Long> {

    @Query("select distinct(r.code) code from sys_role r " +
            "left join sys_user_role ur on ur.r_id = r.id " +
            "left join sys_user u on u.id = ur.u_id " +
            "where u.username = ?1")
    List<String> queryCodeByUsername(String username);

}
