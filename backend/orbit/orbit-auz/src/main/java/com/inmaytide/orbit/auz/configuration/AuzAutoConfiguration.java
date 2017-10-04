package com.inmaytide.orbit.auz.configuration;

import com.inmaytide.orbit.auz.cache.RedisSessionDao;
import com.inmaytide.orbit.auz.cache.RedisShiroCacheManager;
import com.inmaytide.orbit.auz.filter.AuthenticatingFilter;
import com.inmaytide.orbit.auz.helper.CorsProperties;
import com.inmaytide.orbit.auz.provider.CaptchaProvider;
import com.inmaytide.orbit.auz.provider.DefaultCaptchaProvider;
import com.inmaytide.orbit.auz.realm.FormRealm;
import com.inmaytide.orbit.auz.realm.JWTRealm;
import com.inmaytide.orbit.auz.strategy.FirstExceptionStrategy;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.annotation.Resource;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moss
 * @since October 04, 2017
 */
@Configuration
public class AuzAutoConfiguration {

    @Resource
    private CorsProperties corsProperties;

    @Bean
    @ConditionalOnMissingBean(CaptchaProvider.class)
    public ConfigurableCaptchaService configurableCaptchaService() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setHeight(50);
        return cs;
    }

    @Bean
    @ConditionalOnMissingBean(CaptchaProvider.class)
    public CaptchaProvider captchaProvider(ConfigurableCaptchaService configurableCaptchaService, StringRedisTemplate stringRedisTemplate) {
        return new DefaultCaptchaProvider(configurableCaptchaService, stringRedisTemplate);
    }

    @Bean
    public Authorizer authorizer() {
        return new ModularRealmAuthorizer();
    }

    @Bean
    public AuthenticationStrategy authenticationStrategy() {
        return new FirstExceptionStrategy();
    }

    @Bean
    protected Authenticator authenticator(AuthenticationStrategy authenticationStrategy) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(authenticationStrategy);
        return authenticator;
    }

    @Bean
    public RedisSessionDao sessionDAO() {
        return new RedisSessionDao();
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDao sessionDao) {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        corsConfigurationSource.registerCorsConfiguration(corsProperties.getMapping(), corsProperties.translate());
        return new CorsWebFilter(corsConfigurationSource);
    }

    @Bean
    public AuthenticatingFilter authenticatingFilter() {
        return new AuthenticatingFilter();
    }

    @Bean
    public Map<String, String> filterChainMap() {
        Map<String, String> map = new HashMap<>();
        map.put("/login", "anon");
        map.put("/captcha", "anon");
        map.put("/lang/*", "anon");
        map.put("/**", "authc");
        return map;
    }

    @Bean
    public DefaultSecurityManager securityManager(RedisShiroCacheManager cacheManager, SessionManager sessionManager,
                                                  JWTRealm jwtRealm, FormRealm formRealm, Authenticator authenticator) {
        DefaultSecurityManager bean = new DefaultSecurityManager();
        bean.setCacheManager(cacheManager);
        bean.setSessionManager(sessionManager);
        bean.setAuthenticator(authenticator);
        bean.setRealms(List.of(jwtRealm, formRealm));
        SecurityUtils.setSecurityManager(bean);
        return bean;
    }


}
