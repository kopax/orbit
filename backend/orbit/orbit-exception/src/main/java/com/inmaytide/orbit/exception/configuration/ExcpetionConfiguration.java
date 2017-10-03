package com.inmaytide.orbit.exception.configuration;

import com.inmaytide.orbit.exception.handler.ErrorHandler;
import com.inmaytide.orbit.exception.handler.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

/**
 * @author Moss
 * @since October 02, 2017
 */
@Configuration
public class ExcpetionConfiguration {

    @Bean
    public HttpHandler exceptionHandler(RouterFunction routers, GlobalExceptionHandler exceptionHandler) {
        return WebHttpHandlerBuilder
                .webHandler(RouterFunctions.toWebHandler(routers))
                .exceptionHandler(exceptionHandler).build();
    }

    @Bean
    @ConditionalOnMissingBean(ErrorHandler.class)
    public ErrorHandler errorHandler() {
        return new ErrorHandler();
    }



}
