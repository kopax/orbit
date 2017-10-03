package com.inmaytide.orbit.service.basic;

import com.inmaytide.orbit.exception.VersionMatchedException;
import com.inmaytide.orbit.domain.basic.BasicEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * @author Moss
 * @since September 15, 2017
 */
public interface BasicService {

    String DEFAULT_SEPARATOR_CHARS = ",";

    Integer START_VERSION = 0;

    /**
     * Splits the provided text into specifies types array,
     * separators to see {@link DEFAULT_SEPARATOR_CHARS}.
     */
    default <T> T[] split(final String text, Function<String, T> mapper, IntFunction<T[]> generator) {
        Assert.hasText(text, "[Assertion failed] - the id string must have text; it must not be null, empty, or blank");
        String[] splitted = StringUtils.split(text, DEFAULT_SEPARATOR_CHARS);
        return Arrays.stream(splitted).map(mapper).toArray(generator);
    }


    default <T extends BasicEntity> void matchVersion(T modified, T original) {
        if (modified == null || original == null
                || !Objects.equals(modified.getVersion(), original.getVersion())) {
            throw new VersionMatchedException();
        }
    }


}
