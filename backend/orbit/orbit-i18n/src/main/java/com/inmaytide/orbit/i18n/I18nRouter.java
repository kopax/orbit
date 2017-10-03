package com.inmaytide.orbit.i18n;

import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Moss
 * @since October 03, 2017
 */
public class I18nRouter {

    private I18nResourceProvider provider;

    public I18nRouter(I18nResourceProvider provider) {
        this.provider = provider;
    }

    public RouterFunction<?> routers() {
        return route(GET("/lang/{lang}").and(accept(APPLICATION_JSON)), provider::lang);
    }

}
