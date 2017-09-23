package com.inmaytide.orbit.exception.handler;

import com.inmaytide.orbit.i18n.I18nMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private I18nMessages i18n;

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        ResponseEntity<Object> entity = this.handleException(e, request);
        return this.handleExceptionInternal(e, null, entity.getHeaders(), entity.getStatusCode(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseError error = ResponseErrorBuilders.getBuilder(e).build(e, status);
        status = HttpStatus.valueOf(error.getCode());
        error.setMessage(i18n.get(error.getMessage(), error.getMessage()));
        log.error(e.getClass().getName(), e);
        return super.handleExceptionInternal(e, error, headers, status, request);
    }


}
