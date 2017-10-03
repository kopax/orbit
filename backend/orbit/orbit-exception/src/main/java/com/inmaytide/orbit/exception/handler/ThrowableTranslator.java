package com.inmaytide.orbit.exception.handler;

import com.inmaytide.orbit.i18n.I18nMessages;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * @author Moss
 * @since October 03, 2017
 */
public class ThrowableTranslator {

    private final ResponseError error;

    private ThrowableTranslator(final Throwable throwable) {
        error = ResponseErrorBuilders.getBuilder(throwable).build(throwable, HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(I18nMessages.getInstance().get(error.getMessage(), error.getMessage()));
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(error.getCode());
    }

    public String getMessage() {
        return error.getMessage();
    }

    public ResponseError getError() {
        return error;
    }

    public static <T extends Throwable> Mono<ThrowableTranslator> translate(final Mono<T> throwable) {
        return throwable.flatMap(error -> Mono.just(new ThrowableTranslator(error)));
    }

}
