package com.inmaytide.orbit.dao.sys;

import com.inmaytide.orbit.model.sys.Log;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

import java.util.List;

public interface LogRepository extends MybatisRepository<Log, Long> {

}
