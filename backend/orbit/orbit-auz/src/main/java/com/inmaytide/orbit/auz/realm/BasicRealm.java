package com.inmaytide.orbit.auz.realm;

import com.inmaytide.orbit.auz.provider.PermissionProvider;
import com.inmaytide.orbit.auz.provider.RoleProvider;
import com.inmaytide.orbit.auz.provider.UserProvider;
import com.inmaytide.orbit.domain.sys.User;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public abstract class BasicRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private UserProvider userProvider;

    @Resource
    @Lazy
    private RoleProvider roleProvider;

    @Resource
    @Lazy
    private PermissionProvider permissionProvider;

    protected BasicRealm() {
        setCachingEnabled(true);
        setAuthenticationCachingEnabled(true);
        setAuthorizationCachingEnabled(true);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Assert.notNull(principals, "PrincipalCollection method argument cannot be null.");
        User user = (User) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(getRoleProvider().findCodesByUsername(user.getUsername()));
        info.setStringPermissions(getPermissionProvider().findCodesByUsername(user.getUsername()));
        return info;
    }

    protected UserProvider getUserProvider() {
        return userProvider;
    }

    protected RoleProvider getRoleProvider() {
        return roleProvider;
    }

    protected PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }
}
