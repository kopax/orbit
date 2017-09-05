package com.inmaytide.orbit.web.auth.realm;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.service.sys.RoleService;
import com.inmaytide.orbit.service.sys.UserService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Objects;

public abstract class BasicRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private UserService userService;

    @Resource
    @Lazy
    private RoleService roleService;

    @Resource
    @Lazy
    private PermissionService permissionService;

    public BasicRealm() {
        setCachingEnabled(true);
        setAuthenticationCacheName(Constants.AUTHORIZATION_CACHE_NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Assert.notNull(principals, "PrincipalCollection method argument cannot be null.");
        User user = (User) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(getRoleService().findCodesByUsername(user.getUsername()));
        info.setStringPermissions(getPermissionService().findCodesByUsername(user.getUsername()));
        return info;
    }

    public UserService getUserService() {
        return userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }
}
