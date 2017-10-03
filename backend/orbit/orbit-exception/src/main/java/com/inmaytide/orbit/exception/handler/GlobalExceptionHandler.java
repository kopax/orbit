package com.inmaytide.orbit.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.inmaytide.orbit.i18n.I18nMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler implements WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final I18nMessages i18n;

    private final ObjectWriter writer;

    public GlobalExceptionHandler(I18nMessages i18n) {
        ObjectMapper mapper = new ObjectMapper();
        this.i18n = i18n;
        this.writer = mapper.writerFor(ResponseError.class);
    }


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable e) {
        log.error(e.getClass().getName(), e);
        ServerHttpResponse response = exchange.getResponse();
        ResponseError error = ResponseErrorBuilders.getBuilder(e).build(e, response.getStatusCode());
        HttpStatus status = HttpStatus.valueOf(error.getCode());
        error.setMessage(i18n.get(error.getMessage(), error.getMessage()));
        response.setStatusCode(status);
        try {
            return response.writeAndFlushWith(Mono.just(Mono.just(response.bufferFactory().wrap(writer.writeValueAsBytes(error)))));
        } catch (JsonProcessingException ex) {
            log.error("handling exception failure => ", ex);
        }
        return Mono.empty();
    }
}
