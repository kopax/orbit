package com.inmaytide.orbit.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Frontend i18n resource provider
 *
 * @author Moss
 * @since September 11, 2017
 */
public class I18nResourceProvider {

    @Autowired
    private I18nResourceHolder resourceHolder;


    public Mono<ServerResponse> lang(ServerRequest request) {
        String lang = request.pathVariable("lang");
        return ServerResponse.ok().body(Mono.justOrEmpty(resourceHolder.getResources(lang)), Map.class);
    }

}
