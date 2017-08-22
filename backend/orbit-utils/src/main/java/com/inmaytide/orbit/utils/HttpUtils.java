package com.inmaytide.orbit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {

    public static void enableCros(HttpServletResponse httpResponse,
                               HttpServletRequest httpRequest, String origin) {
        httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }

    public static void disableResponseCache(HttpServletResponse response) {
        response.setDateHeader("Expires", 0L);
        response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.addHeader("Pragma", "no-cache");
    }

}
