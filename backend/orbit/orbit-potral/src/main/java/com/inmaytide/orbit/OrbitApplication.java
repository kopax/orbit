package com.inmaytide.orbit;

import com.inmaytide.orbit.auz.filter.AuthenticatingFilter;
import com.inmaytide.orbit.auz.handler.AuzHandler;
import com.inmaytide.orbit.exception.handler.ErrorHandler;
import com.inmaytide.orbit.exception.handler.GlobalExceptionHandler;
import com.inmaytide.orbit.i18n.I18nRouter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Moss
 * @since October 02, 2017
 */
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@EnableWebFlux
public class OrbitApplication {

    @Value("#{ @environment['server.root-path'] ?: '/' }")
    private String rootPath;

    @Bean
    public RouterFunction<?> routers(I18nRouter i18nRouter,
                                     ErrorHandler errorHandler,
                                     AuzHandler auzHandler) {
        return nest(RequestPredicates.path(rootPath), i18nRouter.routers()
                .andOther(auzHandler.routers())
                .andOther(route(RequestPredicates.all(), errorHandler::notFound)));
    }

    @Bean
    public HttpHandler httpHandler(RouterFunction routers,
                                   CorsWebFilter corsWebFilter,
                                   AuthenticatingFilter authenticatingFilter,
                                   GlobalExceptionHandler exceptionHandler) {
        return WebHttpHandlerBuilder
                .webHandler(RouterFunctions.toWebHandler(routers))
                .filter(corsWebFilter, authenticatingFilter)
                .exceptionHandler(exceptionHandler).build();
    }

    public static void main(String... args) {
        SpringApplication.run(OrbitApplication.class, args);
    }

}
