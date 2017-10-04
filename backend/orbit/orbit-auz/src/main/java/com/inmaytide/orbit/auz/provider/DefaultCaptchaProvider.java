package com.inmaytide.orbit.auz.provider;

import com.inmaytide.orbit.consts.Constants;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

public class DefaultCaptchaProvider implements CaptchaProvider {

    private ConfigurableCaptchaService configurableCaptchaService;

    private StringRedisTemplate stringRedisTemplate;

    public DefaultCaptchaProvider(ConfigurableCaptchaService configurableCaptchaService, StringRedisTemplate stringRedisTemplate) {
        this.configurableCaptchaService = configurableCaptchaService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void generateCaptcha(String format, OutputStream os, String keySuffix) throws IOException {
        String captcha = EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService, "png", os);
        stringRedisTemplate.opsForValue().set(generateCacheCaptchaKey(keySuffix), captcha);
    }

    @Override
    public void validation(String captcha, String keySuffix, Supplier<? extends RuntimeException> exceptionSupplier) {
        String key = generateCacheCaptchaKey(keySuffix);
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

    private String generateCacheCaptchaKey(String keySuffix) {
        return String.format(Constants.CACHE_CAPTCHA_KEY_PATTERN, keySuffix);
    }

}
