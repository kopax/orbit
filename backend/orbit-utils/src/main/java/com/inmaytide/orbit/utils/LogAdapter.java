package com.inmaytide.orbit.utils;

import org.slf4j.Logger;

public interface LogAdapter {

    Logger getLogger();

    default void debug(String message, Object... args) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(message, args);
        }
    }

    default void info(String message, Object... args) {
        if (getLogger().isInfoEnabled()) {
            getLogger().info(message, args);
        }
    }

    default void info(String message, Throwable throwable) {
        if (getLogger().isInfoEnabled()) {
            getLogger().info(message, throwable);
        }
    }

}
