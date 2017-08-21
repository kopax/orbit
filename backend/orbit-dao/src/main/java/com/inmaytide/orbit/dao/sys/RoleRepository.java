package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Role;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends MybatisRepository<Role, String> {

    String DEFAULT_NAMESPACE = "com.inmaytide.orbit.dao.sys.RoleRepository";

    @Query(namespace = DEFAULT_NAMESPACE, value = "findCodesByUsername")
    List<String> findCodesByUsername(@Param("username") String username);

}
