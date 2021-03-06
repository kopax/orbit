package com.inmaytide.orbit.auz.realm;

import com.inmaytide.orbit.auz.cache.SimpleByteSource;
import com.inmaytide.orbit.auz.helper.Md5Helper;
import com.inmaytide.orbit.auz.provider.CaptchaProvider;
import com.inmaytide.orbit.auz.token.UsernamePasswordCaptchaToken;
import com.inmaytide.orbit.exception.auz.IncorrectCaptchaException;
import com.inmaytide.orbit.domain.sys.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component
public class FormRealm extends BasicRealm {

    @Resource
    private CaptchaProvider captchaService;

    public FormRealm() {
        super();
        setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof UsernamePasswordCaptchaToken;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Assert.notNull(authenticationToken, "AuthenticationToken method argument cannot be null.");
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authenticationToken;
        String username = token.getUsername();
        Assert.hasText(username, "Null usernames are not allowed by this realm");
        captchaService.validation(token.getCaptcha(), token.getCaptchaKey(), IncorrectCaptchaException::new);
        User user = getUserProvider().findByUsername(username).orElseThrow(UnknownAccountException::new);
        if (user.isLocked()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt(username), getName());
    }

    private ByteSource credentialsSalt(String username) {
        return new SimpleByteSource(Md5Helper.salt(username));
    }

}
