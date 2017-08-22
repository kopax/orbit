package com.inmaytide.orbit.utils;

import com.inmaytide.orbit.consts.Constants;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

@Component
public class CaptchaUtils {

    @Resource
    private ConfigurableCaptchaService configurableCaptchaService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码存入redis并输出图片
     *
     * @param format 生成的图片格式
     * @param os     输出流
     * @param v      验证码标识
     * @throws IOException
     */
    public void generateCaptcha(String format, OutputStream os, String v) throws IOException {
        String words = EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService, "png", os);
        stringRedisTemplate.opsForValue().set(generateCacheCaptchaKey(v), words);
    }

    public void validation(String captcha, String v, Supplier<? extends RuntimeException> exceptionSupplier) {
        String key = generateCacheCaptchaKey(v);
        if (stringRedisTemplate.hasKey(key)
                && !StringUtils.endsWithIgnoreCase(captcha, getServerCaptcha(key))) {
            throw exceptionSupplier.get();
        }
    }

    private String getServerCaptcha(String key) {
        String serverCaptcha = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);
        return serverCaptcha;
    }

    private String generateCacheCaptchaKey(String v) {
        return StringUtils.arrayToDelimitedString(new String[]{Constants.CACHE_CAPTCHA_KEY, v}, "-");
    }

}
