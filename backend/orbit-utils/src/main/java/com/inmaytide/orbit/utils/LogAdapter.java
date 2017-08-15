package com.inmaytide.orbit.utils;

import org.slf4j.Logger;

public interface LogAdapter {

    Logger getLogger();

    default void debug(String message, Object... args) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(message, args);
        }
    }

}
