package com.inmaytide.orbit.exception.handler;

import com.inmaytide.orbit.exception.PathNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Moss
 * @since October 03, 2017
 */
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    public Mono<ServerResponse> notFound(final ServerRequest request) {
        log.error("The path => {} not found.", request.path());
        return Mono.just(new PathNotFoundException(request.path())).transform(this::getResponse);
    }

    private <T extends Throwable> Mono<ServerResponse> getResponse(final Mono<T> monoError) {
        return monoError.transform(ThrowableTranslator::translate)
                .flatMap(translation -> ServerResponse
                        .status(translation.getHttpStatus())
                        .body(Mono.just(translation.getError()), ResponseError.class));
    }

}
