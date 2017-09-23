package com.inmaytide.orbit.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.UUID;

public class CommonUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * Convert an object to int, the object must not be null.
     */
    public static int toint(Object o) {
        if (o == null || !NumberUtils.isCreatable(o.toString())) {
            throw new IllegalArgumentException();
        }
        return Integer.valueOf(o.toString());
    }

}
