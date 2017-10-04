package com.inmaytide.orbit.auz.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

/**
 * @author Moss
 * @since October 04, 2017
 */
public interface CaptchaProvider {

    void generateCaptcha(String format, OutputStream os, String v) throws IOException;

    void validation(String captcha, String v, Supplier<? extends RuntimeException> exceptionSupplier);

}
