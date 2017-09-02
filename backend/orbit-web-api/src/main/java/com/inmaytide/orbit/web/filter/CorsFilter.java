package com.inmaytide.orbit.web.filter;

import com.inmaytide.orbit.http.HttpHelper;
import com.inmaytide.orbit.http.CorsProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    private CorsProperties corsProperties;

    public CorsFilter(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpHelper.enableCors(httpResponse, corsProperties);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
