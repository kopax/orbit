package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface RoleRepository extends MybatisRepository<Role, Long> {

    @Query("findCodesByUsername")
    List<String> findCodesByUsername(@Param("username") String username);

}
