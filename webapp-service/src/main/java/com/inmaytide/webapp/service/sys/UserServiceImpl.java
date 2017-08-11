package com.inmaytide.webapp.service.sys;

import com.inmaytide.webapp.dao.sys.UserRepository;
import com.inmaytide.webapp.model.sys.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        User user = userRepository.findOne(id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.queryByUsername(username));
    }
}