package com.inmaytide.orbit.web.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmaytide.orbit.utils.TokenUtil;
import com.inmaytide.orbit.web.auth.exception.CannotCreateTokenException;
import com.inmaytide.orbit.web.auth.exception.InvalidTokenException;
import com.inmaytide.orbit.web.auth.token.JWTAuthenticationToken;
import com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken;
import io.jsonwebtoken.Claims;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTOrAuthenticationFilter extends AuthenticatingFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CAPTCHA = "captcha";

    public JWTOrAuthenticationFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        String previous = getLoginUrl();
        if (!Objects.isNull(previous)) {
            appliedPaths.remove(previous);
        }
        super.setLoginUrl(loginUrl);
        appliedPaths.put(getLoginUrl(), null);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean loggedIn = false;
        System.out.println(request.getAttribute("username"));
        if (isLoginRequest(request, response) || isLoggedAttempt(request, response)) {
            loggedIn = executeLogin(request, response);
        }

        if (!loggedIn) {
            throw new AuthenticationException();
        }

        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            String json = IOUtils.toString(request.getInputStream());
            if (StringUtils.isNotEmpty(json)) {
                ObjectMapper mapper = new ObjectMapper();
                Map m = mapper.readValue(json, Map.class);
                String username = Objects.toString(m.get(USERNAME), null);
                String password = Objects.toString(m.get(PASSWORD), null);
                String captcha = Objects.toString(m.get(CAPTCHA), null);
                return new UsernamePasswordCaptchaToken(username, password, captcha);
            }
        }
        
        if (isLoggedAttempt(request, response)) {
            String token = getAuthzHeader(request);
            if(StringUtils.isNotEmpty(token)) {
                return createToken(token);
            }
        }

        throw new CannotCreateTokenException();
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        throw e;
    }

    private AuthenticationToken createToken(String token) {
        try {
            Claims claims = TokenUtil.getClaims(token);
            return new JWTAuthenticationToken(claims.getSubject(), token);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    protected boolean isLoggedAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    protected  String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }
}
