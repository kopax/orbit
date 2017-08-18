package com.inmaytide.orbit.web.auth.realm;

import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.TokenUtil;
import com.inmaytide.orbit.web.auth.exception.InvalidTokenException;
import com.inmaytide.orbit.web.auth.token.JWTAuthenticationToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class JWTRealm extends BasicRealm {

    public JWTRealm() {
        super();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JWTAuthenticationToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTAuthenticationToken token = (JWTAuthenticationToken) authenticationToken;
        Optional<User> user = getUserService().findByUsername(Objects.toString(token.getUserId()));
        User u = user.orElseThrow(InvalidTokenException::new);
        if (TokenUtil.validateToken(token.getToken())) {
            SimpleAccount account = new SimpleAccount(u, token.getToken(), getName());
            //account.addRole(getRoleService().findCodesByUsername(u.getUsername()));
            //account.addStringPermissions(getPermissionService().findCodesByUsername(u.getUsername()));
            account.addStringPermission("user:get");
            return account;
        }
        throw new InvalidTokenException();
    }
}
