package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.UserRepository;
import com.inmaytide.orbit.model.sys.User;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractCrudService<UserRepository, User, String> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(getRepository().queryByUsername(username));
    }
}
