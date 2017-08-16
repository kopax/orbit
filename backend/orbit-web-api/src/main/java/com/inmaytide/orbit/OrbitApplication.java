package com.inmaytide.orbit;

import com.inmaytide.orbit.web.auth.ShiroRealm;
import com.inmaytide.orbit.web.auth.cache.RedisSessionDao;
import com.inmaytide.orbit.web.auth.cache.RedisShiroCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.awt.*;
import java.nio.charset.Charset;

@SpringBootApplication
public class OrbitApplication {

    @Value("${spring.messages.basename}")
    private String basename;

    @Value("${spring.messages.cache-seconds}")
    private String cacheSeconds;

    @Bean
    public WebMvcConfigurerAdapter corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDao sessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm realm, RedisShiroCacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        bean.setRealm(realm);
        bean.setCacheManager(cacheManager);
        bean.setSessionManager(sessionManager);
        return bean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition inst = new DefaultShiroFilterChainDefinition();
        inst.addPathDefinition("/css/**", "anon");
        inst.addPathDefinition("/js/**", "anon");
        inst.addPathDefinition("/login", "anon");
        inst.addPathDefinition("/captcha", "anon");
        inst.addPathDefinition("/**", "authc");
        return inst;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        if (StringUtils.hasText(this.basename)) {
            messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
                    StringUtils.trimAllWhitespace(this.basename)));
        }
        messageSource.setDefaultEncoding(Charset.forName("UTF-8").name());
        messageSource.setFallbackToSystemLocale(true);
        messageSource.setCacheSeconds(-1);
        messageSource.setAlwaysUseMessageFormat(false);
        return messageSource;
    }

    @Bean
    public ConfigurableCaptchaService configurableCaptchaService() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setHeight(50);
        return cs;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrbitApplication.class, args);
    }
}
