package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.http.HttpHelper;
import com.inmaytide.orbit.props.CorsProperties;
import com.inmaytide.orbit.web.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@RestController
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PAGE = "/error";

    private final ErrorAttributes errorAttributes;

    @Resource
    private CorsProperties corsProperties;

    @Resource
    private GlobalExceptionHandler exceptionHandler;

    @Autowired
    public GlobalErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ERROR_PAGE)
    public Object handleError(HttpServletRequest request, HttpServletResponse response) {
        Throwable t = getError(request).getCause();
        HttpHelper.enableCors(response, corsProperties);
        return exceptionHandler.defaultErrorHandler(request, t);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE;
    }

    private Throwable getError(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getError(requestAttributes);
    }
}
