package com.inmaytide.orbit.auz.handler;

import com.inmaytide.orbit.auz.token.UsernamePasswordCaptchaToken;
import com.inmaytide.orbit.domain.sys.User;
import com.inmaytide.orbit.log.LogAnnotation;
import com.inmaytide.orbit.utils.CommonUtils;
import com.inmaytide.orbit.utils.TokenUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author Moss
 * @since October 04, 2017
 */
@Component
public class AuzHandler {

    private static final Logger log = LoggerFactory.getLogger(AuzHandler.class);

    @LogAnnotation(value = "系统登录", success = "登录成功", failure = "登录失败")
    public Mono<ServerResponse> login(ServerRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Mono<UsernamePasswordCaptchaToken> token = request.bodyToMono(UsernamePasswordCaptchaToken.class);
        subject.login(token.block());
        User user = (User) subject.getPrincipal();
        user.setToken(TokenUtils.generate(CommonUtils.uuid(), user.getUsername()));
        log.debug("{} login succeed.", user.getUsername());
        return ok().body(Mono.just(user), User.class);
    }

    @LogAnnotation(value = "系统登录", success = "登录成功", failure = "登录失败")
    public Mono<ServerResponse> bbc(ServerRequest request) {
        return ok().body(Mono.just("123123123"), String.class);
    }

    public RouterFunction<?> routers() {
        return route(POST("/login").and(accept(APPLICATION_JSON)), this::login)
                .and(route(GET("/bbc"), this::bbc));
    }

    public void captcha(ServerRequest response, String v) throws IOException {
//        response.setContentType("image/png");
//        try (OutputStream os = response.getOutputStream()) {
//            captchaProvider.generateCaptcha("png", os, v);
//        }
    }

}
