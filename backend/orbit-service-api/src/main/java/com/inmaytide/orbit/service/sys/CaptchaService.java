package com.inmaytide.orbit.service.sys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

public interface CaptchaService {


    /**
     * 生成验证码存入redis并输出图片
     *
     * @param format 生成的图片格式
     * @param os     输出流
     * @param v      验证码标识
     * @throws IOException
     */
    void generateCaptcha(String format, OutputStream os, String v) throws IOException;

    void validation(String captcha, String v, Supplier<? extends RuntimeException> exceptionSupplier);


}
