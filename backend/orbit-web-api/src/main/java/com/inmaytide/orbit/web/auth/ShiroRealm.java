package com.inmaytide.orbit.web.auth;

import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.service.sys.RoleService;
import com.inmaytide.orbit.service.sys.UserService;
import com.inmaytide.orbit.utils.CommonUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
public class ShiroRealm extends AuthorizingRealm {

    public static final String AUTHORIZATION_CACHE_NAME = "auth_cache";

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    public ShiroRealm() {
        setCachingEnabled(true);
        setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
        setAuthenticationCacheName(AUTHORIZATION_CACHE_NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Assert.notNull(principals, "PrincipalCollection method argument cannot be null.");
        String username = (String) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleService.findCodesByUsername(username));
        info.setStringPermissions(permissionService.findCodesByUsername(username));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Assert.notNull(authenticationToken, "AuthenticationToken method argument cannot be null.");
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authenticationToken;
        String username = token.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new AccountException("Null usernames are not allowed by this realm");
        }
        User user = userService.findByUsername(username).orElseThrow(UnknownAccountException::new);
        if (user.isLocked()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(),
                ByteSource.Util.bytes(CommonUtils.salt(username)), getName());
    }

}
