package com.inmaytide.webapp.dao.sys;

import com.inmaytide.webapp.model.sys.User;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

public interface UserRepository extends MybatisRepository<User, Long> {

    User queryByUsername(String username);

}
