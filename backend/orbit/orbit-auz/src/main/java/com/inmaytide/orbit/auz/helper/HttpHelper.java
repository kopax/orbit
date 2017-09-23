package com.inmaytide.orbit.auz.helper;

import javax.servlet.http.HttpServletResponse;

public class HttpHelper {

    public static void enableCors(HttpServletResponse response, CorsProperties props) {
        response.setHeader("Access-Control-Allow-Origin", props.getAllowedOrigins());
        response.setHeader("Access-Control-Allow-Methods", props.getAllowedMethods());
        response.setHeader("Access-Control-Max-Age", props.getMaxAge());
        response.setHeader("Access-Control-Allow-Headers", props.getAllowedHeaders());
        response.setHeader("Access-Control-Allow-Credentials", props.getAllowCredentials());
    }

    public static void disableResponseCache(HttpServletResponse response) {
        response.setDateHeader("Expires", 0L);
        response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.addHeader("Pragma", "no-cache");
    }

}
