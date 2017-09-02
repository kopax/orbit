package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Role;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends MybatisRepository<Role, String> {

    @Query(value = "findCodesByUsername")
    List<String> findCodesByUsername(@Param("username") String username);

}
