package com.inmaytide.orbit.web.exception.handler;

import com.inmaytide.orbit.adepter.LogAdapter;
import com.inmaytide.orbit.http.ErrorResult;
import com.inmaytide.orbit.http.RestResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController implements LogAdapter {

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

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,  HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        RestResponse restResponse = getRestResponse(request, status, model);
        model.put("restResponse", restResponse);
        model.put(KEY_EXCEPTION, restResponse.getError().getType());
        model.put(KEY_MESSAGE, restResponse.getError().getMessage());
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<RestResponse> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<RestResponse>(getRestResponse(request, status, body), status);
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * Provide access to the error properties.
     * @return the error properties
     */
    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    private RestResponse getRestResponse(HttpServletRequest request,
                                         HttpStatus status,
                                         Map<String, Object> body) {
        debug(body.toString());
        ErrorResult error = new ErrorResult();
        if (status == HttpStatus.NOT_FOUND) {
            error.setType(NoHandlerFoundException.class.getName());
            error.setMessage(body.get("path").toString());
        } else {
            Object object = request.getAttribute("javax.servlet.error.exception");
            if (object != null && object instanceof Exception) {
                Exception exception = (Exception) object;
                if (exception.getCause() != null) {
                    error = ErrorResult.of(exception.getCause());
                } else {
                    error = ErrorResult.of(exception);
                }
            } else {
                error.setType(Objects.toString(body.get(KEY_EXCEPTION), "Unknow exception"));
                error.setMessage(Objects.toString(body.get(KEY_MESSAGE), "Unknow message"));
            }
        }
        return RestResponse.of(status, error);
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

    @Override
    public Logger getLogger() {
        return log;
    }
}
