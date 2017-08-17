package com.inmaytide.orbit.web.auth.realm;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException;
import com.inmaytide.orbit.web.auth.SessionHelper;
import com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
public class FormRealm extends BasicRealm {

    public FormRealm() {
        super();
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
        if (StringUtils.isEmpty(username)) {
            throw new AccountException("Null usernames are not allowed by this realm");
        }

        Optional<String> optional = SessionHelper.getSessionAttribute(Constants.SESSION_CAPTCHA_KEY);
        optional.ifPresent(captcha -> {
            if (!StringUtils.equalsIgnoreCase(captcha, token.getCaptcha())) {
                throw new IncorrectCaptchaException();
            }
        });

        User user = getUserService().findByUsername(username).orElseThrow(UnknownAccountException::new);
        if (user.isLocked()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(),
                ByteSource.Util.bytes(CommonUtils.salt(username)), getName());
    }

}
