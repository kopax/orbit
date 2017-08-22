package com.inmaytide.orbit.web.auth.realm;

import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.CaptchaUtils;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException;
import com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class FormRealm extends BasicRealm {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CaptchaUtils captchaUtils;

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
        captchaUtils.validation(token.getCaptcha(), token.getCaptchaKey(), IncorrectCaptchaException::new);
        User user = getUserService().findByUsername(username).orElseThrow(UnknownAccountException::new);
        if (user.isLocked()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt(username), getName());
    }

    private ByteSource credentialsSalt(String username) {
        return ByteSource.Util.bytes(CommonUtils.salt(username));
    }

}
