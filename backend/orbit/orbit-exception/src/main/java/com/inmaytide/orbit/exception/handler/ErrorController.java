package com.inmaytide.orbit.exception.handler;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    /**
     * exception
     */
    private static final String KEY_EXCEPTION = "exception";

    /**
     * message
     */
    private static final String KEY_MESSAGE = "message";

    private final ErrorProperties errorProperties;

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        this(errorAttributes, serverProperties.getError());
    }

    public ErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties,
                Collections.emptyList());
    }

    public ErrorController(ErrorAttributes errorAttributes,
                           ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<ResponseError> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(getResponseError(request, status, body), status);
    }

    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * Provide access to the error properties.
     *
     * @return the error properties
     */
    private ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    private ResponseError getResponseError(HttpServletRequest request,
                                           HttpStatus status,
                                           Map<String, Object> body) {
        log.debug(body.toString());
        ResponseError error = new ResponseError();
        if (status == HttpStatus.NOT_FOUND) {
            error.setType(NoHandlerFoundException.class.getName());
            error.setMessage(body.get("path").toString());
        } else {
            Object object = request.getAttribute("javax.servlet.error.exception");
            if (object != null && object instanceof Exception) {
                Exception exception = (Exception) object;
                error = ResponseError.of(exception.getCause() == null ? exception : exception.getCause());
            } else {
                error.setType(Objects.toString(body.get(KEY_EXCEPTION), "Unknow exception"));
                error.setMessage(Objects.toString(body.get(KEY_MESSAGE), "Unknow message"));
            }
        }
        error.setCode(status.value());
        return error;
    }

    @Override
    protected HttpStatus getStatus(HttpServletRequest request) {
        Object object = request.getAttribute("javax.servlet.error.exception");
        if (object != null && object instanceof Exception) {
            Exception e = (Exception) object;
            if (e instanceof AuthenticationException || e.getCause() instanceof AuthenticationException) {
                return HttpStatus.UNAUTHORIZED;
            }
        }
        return super.getStatus(request);
    }
}
