package com.inmaytide.orbit.auz.filter;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Moss
 * @since October 04, 2017
 */
public class AuthenticatingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {


        return chain.filter(exchange);
    }

}
