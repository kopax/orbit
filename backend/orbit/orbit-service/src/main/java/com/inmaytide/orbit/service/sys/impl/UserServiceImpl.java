package com.inmaytide.orbit.service.sys.impl;

import com.inmaytide.orbit.dao.sys.UserRepository;
import com.inmaytide.orbit.domain.sys.User;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.service.sys.UserService;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractCrudService<UserRepository, User, Long> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(getRepository().queryByUsername(username));
    }

    @Override
    public List<User> findByRoleIds(Long[] roleIds) {
        return null;
    }
}
