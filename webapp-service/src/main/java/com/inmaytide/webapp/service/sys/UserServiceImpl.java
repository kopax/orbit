package com.inmaytide.webapp.service.sys;

import com.inmaytide.webapp.dao.sys.UserRepository;
import com.inmaytide.webapp.model.sys.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }
}
