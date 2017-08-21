package com.inmaytide.orbit.web.filter;

import com.inmaytide.orbit.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ResponseUtils.enableCros(httpResponse, httpRequest, origin);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
