package com.inmaytide.orbit;

import com.inmaytide.orbit.exception.handler.ErrorHandler;
import com.inmaytide.orbit.i18n.I18nRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Moss
 * @since October 02, 2017
 */
@SpringBootApplication
public class OrbitApplication {

    @Value("#{ @environment['server.root-path'] ?: '/' }")
    private String rootPath;

    private final I18nRouter i18nRouter;

    private final ErrorHandler errorHandler;

    @Autowired
    public OrbitApplication(I18nRouter i18nRouter, ErrorHandler errorHandler) {
        this.i18nRouter = i18nRouter;
        this.errorHandler = errorHandler;
    }

    @Bean
    public RouterFunction<?> routers() {
        return nest(RequestPredicates.path(rootPath), i18nRouter.routers()
                .andOther(route(RequestPredicates.all(), errorHandler::notFound)));
    }

    public static void main(String... args) {
        SpringApplication.run(OrbitApplication.class, args);
    }

}
