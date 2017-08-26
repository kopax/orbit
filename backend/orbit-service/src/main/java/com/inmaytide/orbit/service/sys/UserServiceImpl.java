package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.UserRepository;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.SessionHelper;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractCrudService<UserRepository, User, Long> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(getRepository().queryByUsername(username));
    }

    @Override
    public User getCurrent() {
        Object object = SessionHelper.getPrincipal();
        Assert.isInstanceOf(User.class, object);
        return (User) object;
    }
}
