package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Permission;
import org.springframework.data.repository.query.Param;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface PermissionRepository extends MybatisRepository<Permission, Long> {

    @Query(value = "findCodesByUsername")
    List<String> findCodesByUsername(@Param("username") String username);

    @Query(value = "findByUsername")
    List<Permission> findByUsername(@Param("username") String username, @Param("category") String category);

    @Query(value = "deleteBatch")
    void delete(@Param("ids") Long[] ids);

    @Query(value="getSort")
    Integer getSort();

    Integer countByCodeAndIdNot(String code, Long id);
}
