package com.inmaytide.orbit.sys.dao;

import com.inmaytide.orbit.sys.model.User;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

public interface UserRepository extends MybatisRepository<User, Long> {

    User queryByUsername(String username);

}
