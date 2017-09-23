package com.inmaytide.orbit.auz.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmaytide.orbit.auz.helper.CorsProperties;
import com.inmaytide.orbit.auz.helper.HttpHelper;
import com.inmaytide.orbit.auz.token.JWTAuthenticationToken;
import com.inmaytide.orbit.auz.token.UsernamePasswordCaptchaToken;
import com.inmaytide.orbit.exception.auz.CannotCreateTokenException;
import com.inmaytide.orbit.exception.auz.InvalidTokenException;
import com.inmaytide.orbit.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

public class JWTOrAuthenticationFilter extends AuthenticatingFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CAPTCHA = "captcha";
    private static final String CAPTCHA_KEY = "captchaKey";

    private CorsProperties corsProperties;

    public JWTOrAuthenticationFilter(CorsProperties corsProperties) {
        setLoginUrl(DEFAULT_LOGIN_URL);
        this.corsProperties = corsProperties;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            HttpHelper.enableCors(httpResponse, corsProperties);
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
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

        if (isLoginRequest(request, response) || isLoggedAttempt(request)) {
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
            String json = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
            if (StringUtils.isNotEmpty(json)) {
                return createUPCToken(json);
            }
        }

        if (isLoggedAttempt(request)) {
            String token = getAuthzHeader(request);
            if (StringUtils.isNotEmpty(token)) {
                return createToken(token);
            }
        }

        throw new CannotCreateTokenException();
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        throw e;
    }

    private UsernamePasswordCaptchaToken createUPCToken(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map m = mapper.readValue(json, Map.class);
        String username = Objects.toString(m.get(USERNAME), null);
        String password = Objects.toString(m.get(PASSWORD), null);
        String captcha = Objects.toString(m.get(CAPTCHA), null);
        String captchaKey = Objects.toString(m.get(CAPTCHA_KEY), null);
        return new UsernamePasswordCaptchaToken(username, password, captcha, captchaKey);
    }

    private AuthenticationToken createToken(String token) {
        try {
            Claims claims = TokenUtils.getClaims(token);
            return new JWTAuthenticationToken(claims.getSubject(), token);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private boolean isLoggedAttempt(ServletRequest request) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    private String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }
}
